package ee.vladislav.tuum.tuumbanking.mapper;

import ee.vladislav.tuum.tuumbanking.model.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {

    @Select("INSERT INTO transaction(account_id, amount, currency, direction, description, balance_after_transaction) " +
            "VALUES(#{accountId}, #{amount}, #{currency}, #{direction}, #{description}, #{balanceAfterTransaction}) " +
            "RETURNING id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createTransaction(Transaction transaction);

    @Select("SELECT * FROM transaction WHERE id = #{id}")
    Transaction findTransactionById(Long id);

    @Select("SELECT * FROM transaction WHERE account_id = #{accountId}")
    List<Transaction> findTransactionsByAccountId(Long accountId);

}