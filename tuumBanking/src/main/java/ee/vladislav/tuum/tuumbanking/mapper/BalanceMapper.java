package ee.vladislav.tuum.tuumbanking.mapper;

import ee.vladislav.tuum.tuumbanking.model.Balance;
import ee.vladislav.tuum.tuumbanking.model.Currency;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BalanceMapper {

    @Insert("INSERT INTO balance(account_id, available_amount, currency) VALUES(#{accountId}, #{availableAmount}, #{currency})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createBalance(Balance balance);

    @Select("SELECT * FROM balance WHERE id = #{id}")
    Balance findBalanceById(Long id);

    @Select("SELECT * FROM balance WHERE account_id = #{accountId} AND currency = #{currency.name}")
    Balance findBalanceByAccountIdAndCurrency(Long accountId, Currency currency);

    @Select("SELECT * FROM balance WHERE account_id = #{accountId}")
    List<Balance> findBalancesByAccountId(Long accountId);

    @Update("UPDATE balance SET available_amount = #{availableAmount} WHERE id = #{id}")
    int updateBalanceAmount(Balance balance);

}