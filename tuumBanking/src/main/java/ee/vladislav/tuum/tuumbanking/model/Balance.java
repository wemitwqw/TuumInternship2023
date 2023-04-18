package ee.vladislav.tuum.tuumbanking.model;

import java.math.BigDecimal;

public class Balance {
    private Long id;
    private Long accountId;
    private BigDecimal availableAmount;
    private Currency currency;

    public Balance(Long id, Long accountId, BigDecimal availableAmount, Currency currency) {
        this.id = id;
        this.accountId = accountId;
        this.availableAmount = availableAmount;
        this.currency = currency;
    }

    public Balance(Long accountId, BigDecimal availableAmount, Currency currency) {
        this.accountId = accountId;
        this.availableAmount = availableAmount;
        this.currency = currency;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
