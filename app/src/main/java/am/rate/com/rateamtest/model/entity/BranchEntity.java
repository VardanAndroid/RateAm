package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BranchEntity {

    @SerializedName("head")
    private int mHead;
    @SerializedName("title")
    private TitleEntity mTitleEntity;
    @SerializedName("address")
    private AddressEntity mAddressEntity;
    @SerializedName("contacts")
    private String mContacts;
    @SerializedName("workhours")
    private List<WorkingDayEntity> mWorkingDayEntities;

    public int getHead() {
        return mHead;
    }

    public TitleEntity getTitleEntity() {
        return mTitleEntity;
    }

    public AddressEntity getAddressEntity() {
        return mAddressEntity;
    }

    public String getContacts() {
        return mContacts;
    }

    public List<WorkingDayEntity> getWorkingDayEntities() {
        return mWorkingDayEntities;
    }

    public boolean isHead() {
        return mHead == 1 ? true : false;
    }
}
