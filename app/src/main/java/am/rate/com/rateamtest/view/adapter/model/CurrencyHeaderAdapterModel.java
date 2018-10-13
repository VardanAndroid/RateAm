package am.rate.com.rateamtest.view.adapter.model;

public class CurrencyHeaderAdapterModel implements ListItem {
    @Override
    public ViewHolderType getItemType() {
        return ViewHolderType.CURRENCY_HEADER;
    }

    @Override
    public int getItemId() {
        return 0;
    }
}
