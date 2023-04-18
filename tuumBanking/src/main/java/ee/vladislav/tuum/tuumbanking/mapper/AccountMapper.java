package ee.vladislav.tuum.tuumbanking.mapper;

import ee.vladislav.tuum.tuumbanking.model.Account;
import ee.vladislav.tuum.tuumbanking.model.Balance;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("INSERT INTO account(customer_id, country) VALUES(#{customerId}, #{country}) RETURNING id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createAccount(Account account);

    @Select("SELECT * FROM account WHERE id = #{id}")
    @Results({
            @Result(property = "balances", column = "id", javaType = List.class, many = @Many(select = "findBalancesByAccountId"))
    })
    Account findAccountById(Long id);

    @Select("SELECT * FROM account WHERE customer_id = #{customerId}")
    @Results({
            @Result(property = "balances", column = "id", javaType = List.class, many = @Many(select = "findBalancesByAccountId"))
    })
    List<Account> findAccountsByCustomerId(Long customerId);

    @Select("SELECT * FROM account")
    @Results({
            @Result(property = "balances", column = "id", javaType = List.class, many = @Many(select = "findBalancesByAccountId"))
    })
    List<Account> findAllAccounts();

    @Select("SELECT * FROM balance WHERE account_id = #{accountId}")
    List<Balance> findBalancesByAccountId(Long accountId);

}