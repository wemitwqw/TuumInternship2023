package ee.vladislav.tuum.tuumbanking.requestStructure;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateAccountRequest {

    @JsonProperty(value = "customer_id")
    private Long customerId;

    private String country;

    private List<String> currencies;

    public CreateAccountRequest(Long customerId, String country, List<String> currencies) {
        this.customerId = customerId;
        this.country = country;
        this.currencies = currencies;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public String getCountry() {
        return country;
    }
    public List<String> getCurrencies() {
        return currencies;
    }
}