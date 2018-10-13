package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

public class WorkingDayEntity {

    @SerializedName("days")
    private String mDays;
    @SerializedName("hours")
    private String mHours;

    public String getDays() {
        return mDays;
    }

    public String getHours() {
        return mHours;
    }
}
