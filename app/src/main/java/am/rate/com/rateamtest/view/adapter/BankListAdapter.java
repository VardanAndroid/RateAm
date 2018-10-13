package am.rate.com.rateamtest.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.view.adapter.model.BankAdapterModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.BankListViewHolder> implements View.OnClickListener {

    private List<BankAdapterModel> mBanks;
    private OnBankItemClickListener mListener;
    private Context mContext;

    public BankListAdapter(OnBankItemClickListener listener, Context context) {
        mBanks = new ArrayList<>();
        this.mListener = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public BankListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bank_item_view, parent, false);
        return new BankListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankListViewHolder holder, int position) {
        BankAdapterModel bank = mBanks.get(position);
        holder.mBankNameTv.setText(bank.getName());
        holder.mBuyTv.setText(String.valueOf(bank.getRate().getBuy()));
        holder.mSellTv.setText(String.valueOf(bank.getRate().getSell()));
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        if (bank.getRate().isMaxSell()) {
            holder.mSellTv.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            holder.mSellTv.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
        }

        if (bank.getRate().isMaxBuy()) {
            holder.mBuyTv.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            holder.mBuyTv.setTextColor(ContextCompat.getColor(mContext, android.R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return mBanks.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void notifyChange(List<BankAdapterModel> banks) {
        this.mBanks = banks;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            int position = ((int) view.getTag());
            BankAdapterModel bank = mBanks.get(position);
            mListener.onBankClicked(bank);
        }
    }

    static class BankListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.bankName)
        TextView mBankNameTv;
        @BindView(R.id.sell)
        TextView mSellTv;
        @BindView(R.id.buy)
        TextView mBuyTv;

        public BankListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
