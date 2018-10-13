package am.rate.com.rateamtest.view.model;

import java.io.Serializable;
import java.util.Map;

public class Bank implements Serializable {
    private final String mId;
    private String mName;
    private Map<String, Currency> mCurrencyMap;
    private String mLogo;

    public Bank(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Map<String, Currency> getCurrencyMap() {
        return mCurrencyMap;
    }

    public void setCurrencyMap(Map<String, Currency> currencyMap) {
        mCurrencyMap = currencyMap;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    private boolean hasCurrency(String key) {
        return mCurrencyMap.get(key) != null;
    }

    public boolean hasRateByCashType(boolean isCash, String key) {
        if (!hasCurrency(key)) return false;
        Currency currency = mCurrencyMap.get(key);
        return isCash ? currency.getCash() != null : currency.getNonCash() != null;

    }

}
