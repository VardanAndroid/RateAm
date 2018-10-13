package am.rate.com.rateamtest.view.adapter.model;

import am.rate.com.rateamtest.view.model.Rate;

public class BankAdapterModel {

    private final String mId;
    private final String mName;
    private final Rate mRate;

    public BankAdapterModel(String id, String name, Rate rate) {
        mId = id;
        mName = name;
        mRate = rate;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Rate getRate() {
        return mRate;
    }
}
