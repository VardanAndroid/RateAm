package am.rate.com.rateamtest.model.entity;

import com.google.gson.annotations.SerializedName;

public class TitleEntity {

    @SerializedName("en")
    private String mTitleEn;
    @SerializedName("am")
    private String mTitleAm;
    @SerializedName("ru")
    private String mTitleRu;

    public String getTitleEn() {
        return mTitleEn;
    }

    public String getTitleAm() {
        return mTitleAm;
    }

    public String getTitleRu() {
        return mTitleRu;
    }
}
