package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

public class RateEntity {
    @SerializedName("buy")
    private float mBuy;
    @SerializedName("sell")
    private float mSell;

    public float getBuy() {
        return mBuy;
    }

    public float getSell() {
        return mSell;
    }
}
