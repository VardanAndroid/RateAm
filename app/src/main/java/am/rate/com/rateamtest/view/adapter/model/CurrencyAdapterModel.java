package am.rate.com.rateamtest.view.adapter.model;

import am.rate.com.rateamtest.view.model.Rate;

public class CurrencyAdapterModel implements ListItem {

    private String mKey;
    private Rate mRate;

    public CurrencyAdapterModel(String key, Rate rate) {
        mKey = key;
        mRate = rate;
    }

    public String getKey() {
        return mKey;
    }

    public Rate getRate() {
        return mRate;
    }

    @Override
    public ViewHolderType getItemType() {
        return ViewHolderType.CURRENCY;
    }

    @Override
    public int getItemId() {
        return mKey.hashCode();
    }
}
