package ee.vladislav.tuum.tuumbanking.model;

public enum Currency {

    EUR("EUR"),
    SEK("SEK"),
    GBP("GBP"),
    USD("USD");

    public String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}