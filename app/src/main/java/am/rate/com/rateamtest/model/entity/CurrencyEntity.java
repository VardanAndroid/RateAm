package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

public class CurrencyEntity {

    @SerializedName("0")
    private RateEntity mCashRate;
    @SerializedName("1")
    private RateEntity mNonCashRate;

    public RateEntity getCashRate() {
        return mCashRate;
    }

    public RateEntity getNonCashRate() {
        return mNonCashRate;
    }

    public void setCashRate(RateEntity cashRate) {
        mCashRate = cashRate;
    }

    public void setNonCashRate(RateEntity nonCashRate) {
        mNonCashRate = nonCashRate;
    }
}
