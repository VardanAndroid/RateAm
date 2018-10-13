package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BankEntity {
    @SerializedName("title")
    private String mName;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("list")
    private Map<String, CurrencyEntity> mRates;

    public String getName() {
        return mName;
    }

    public String getLogo() {
        return mLogo;
    }

    public Map<String, CurrencyEntity> getRates() {
        return mRates;
    }
}
