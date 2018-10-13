package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

public class AddressEntity {

    @SerializedName("en")
    private String mAddressEn;
    @SerializedName("am")
    private String mAddressAm;
    @SerializedName("ru")
    private String mAddressRu;

    public String getAddressEn() {
        return mAddressEn;
    }

    public String getAddressAm() {
        return mAddressAm;
    }

    public String getAddressRu() {
        return mAddressRu;
    }
}
