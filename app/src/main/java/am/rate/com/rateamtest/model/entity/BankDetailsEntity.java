package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BankDetailsEntity {

    @SerializedName("date")
    private float mDate;
    @SerializedName("list")
    private Map<String, BranchEntity> mBranchEntityMap;


    public Map<String, BranchEntity> getBranchEntityMap() {
        return mBranchEntityMap;
    }
}
