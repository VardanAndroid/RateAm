package am.rate.com.rateamtest.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.view.adapter.model.CurrencyAdapterModel;
import butterknife.BindView;

public class CurrencyListViewHolder extends BaseViewHolder<CurrencyAdapterModel> {

    @BindView(R.id.currencyName)
    TextView mCurrencyName;
    @BindView(R.id.currencyBuy)
    TextView mCurrencyBuy;
    @BindView(R.id.currencySell)
    TextView mCurrencySell;

    public CurrencyListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(CurrencyAdapterModel currencyAdapterModel) {
        mCurrencyName.setText(currencyAdapterModel.getKey());
        mCurrencyBuy.setText(String.valueOf(currencyAdapterModel.getRate().getBuy()));
        mCurrencySell.setText(String.valueOf(currencyAdapterModel.getRate().getSell()));
    }
}
