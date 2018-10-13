package am.rate.com.rateamtest.view.model;

import java.io.Serializable;

public class Currency implements Serializable{

    private Rate mCash;
    private Rate mNonCash;

    public Currency(Rate cash, Rate nonCash) {
        mCash = cash;
        mNonCash = nonCash;
    }

    public Rate getCash() {
        return mCash;
    }

    public void setCash(Rate cash) {
        mCash = cash;
    }

    public Rate getNonCash() {
        return mNonCash;
    }

    public void setNonCash(Rate nonCash) {
        mNonCash = nonCash;
    }

    public boolean hasCurrency(boolean isCash){
        return isCash ? mCash != null : mNonCash != null;
    }
}
