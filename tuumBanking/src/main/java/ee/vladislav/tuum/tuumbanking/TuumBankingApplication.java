package ee.vladislav.tuum.tuumbanking;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication(scanBasePackages = "ee.vladislav.*")
public class TuumBankingApplication {
	@Autowired
	private static Environment env;
	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername("user");
		factory.setPassword("password");
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare("test.queue", false, false, false, null);
		channel.exchangeDeclare("test.exchange", "direct", true);

		System.out.println("Queue declared successfully.");

		channel.close();
		connection.close();

		SpringApplication.run(TuumBankingApplication.class, args);
	}

}
