package ee.vladislav.tuum.tuumbanking.requestStructure;

import java.math.BigDecimal;

public class CreateTransactionRequest {
    private Long accountId;
    private BigDecimal amount;
    private String currency;
    private String direction;
    private String description;

    public CreateTransactionRequest(Long accountId, BigDecimal amount, String currency, String direction, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
        this.direction = direction;
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDirection() {
        return direction;
    }

    public String getDescription() {
        return description;
    }
}
