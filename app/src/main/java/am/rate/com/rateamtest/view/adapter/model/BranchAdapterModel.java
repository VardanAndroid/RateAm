package am.rate.com.rateamtest.view.adapter.model;

public class BranchAdapterModel implements ListItem {

    private String mName;
    private String mId;

    public BranchAdapterModel(String name, String id) {
        mName = name;
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    @Override
    public ViewHolderType getItemType() {
        return ViewHolderType.BRANCH;
    }

    @Override
    public int getItemId() {
        return mId.hashCode();
    }
}
