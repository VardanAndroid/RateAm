package am.rate.com.rateamtest.view.model;

public class Address {

    private String mAddressEn;
    private String mAddressRu;
    private String mAddressAm;

    public Address(String addressEn, String addressRu, String addressAm) {
        mAddressEn = addressEn;
        mAddressRu = addressRu;
        mAddressAm = addressAm;
    }

    public String getAddressEn() {
        return mAddressEn;
    }

    public String getAddressRu() {
        return mAddressRu;
    }

    public String getAddressAm() {
        return mAddressAm;
    }
}
