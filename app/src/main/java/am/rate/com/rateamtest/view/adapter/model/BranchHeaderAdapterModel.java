package am.rate.com.rateamtest.view.adapter.model;

public class BranchHeaderAdapterModel implements ListItem {
    @Override
    public ViewHolderType getItemType() {
        return ViewHolderType.BRANCH_HEADER;
    }

    @Override
    public int getItemId() {
        return 0;
    }
}
