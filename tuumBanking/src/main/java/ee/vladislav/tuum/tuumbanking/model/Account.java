package ee.vladislav.tuum.tuumbanking.model;


import java.util.List;

public class Account {

    private Long id;
    private Long customerId;
    private String country;
    private List<Balance> balances;

    public Account(Long id, Long customerId, String country, List<Balance> balances) {
        this.id = id;
        this.customerId = customerId;
        this.country = country;
        this.balances = balances;
    }

    public Account(Long id, Long customerId, String country) {
        this.id = id;
        this.customerId = customerId;
        this.country = country;
    }

    public Account(Long customerId, String country) {
        this.customerId = customerId;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }
}