package am.rate.com.rateamtest.view.model;

import java.io.Serializable;

public class Rate implements Serializable {

    private float buy;
    private float sell;
    private boolean isMaxBuy;
    private boolean isMaxSell;

    public Rate(float buy, float sell) {
        this.buy = buy;
        this.sell = sell;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public boolean isMaxBuy() {
        return isMaxBuy;
    }

    public void setMaxBuy(boolean maxBuy) {
        isMaxBuy = maxBuy;
    }

    public boolean isMaxSell() {
        return isMaxSell;
    }

    public void setMaxSell(boolean maxSell) {
        isMaxSell = maxSell;
    }
}
