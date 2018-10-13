package am.rate.com.rateamtest.view.model;

public class Title {
    private String mTitleAm;
    private String mTitleRu;
    private String mTitleEn;

    public Title(String titleAm, String titleRu, String titleEn) {
        mTitleAm = titleAm;
        mTitleRu = titleRu;
        mTitleEn = titleEn;
    }

    public String getTitleAm() {
        return mTitleAm;
    }

    public String getTitleRu() {
        return mTitleRu;
    }

    public String getTitleEn() {
        return mTitleEn;
    }
}
