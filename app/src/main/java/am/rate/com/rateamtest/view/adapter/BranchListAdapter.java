package am.rate.com.rateamtest.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.view.adapter.model.ListItem;
import am.rate.com.rateamtest.view.adapter.model.ViewHolderType;
import am.rate.com.rateamtest.view.adapter.viewholder.BaseViewHolder;
import am.rate.com.rateamtest.view.adapter.viewholder.BrachListViewHolder;
import am.rate.com.rateamtest.view.adapter.viewholder.BranchListViewHeaderHolder;
import am.rate.com.rateamtest.view.adapter.viewholder.CurrencyListViewHeaderHolder;
import am.rate.com.rateamtest.view.adapter.viewholder.CurrencyListViewHolder;

public class BranchListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<ListItem> mListItems;

    public BranchListAdapter() {
        mListItems = new ArrayList<>();
    }

    public void notifyChange(List<ListItem> list) {
        mListItems.addAll(list);
        notifyDataSetChanged();
    }

    public void notifyCurrencyChanged(List<ListItem> list) {
        Iterator iterator = mListItems.iterator();
        while (iterator.hasNext()) {
            ListItem model = ((ListItem) iterator.next());
            if (model.getItemType() == ViewHolderType.CURRENCY || model.getItemType() == ViewHolderType.CURRENCY_HEADER) {
                iterator.remove();
            }
        }
        mListItems.addAll(0, list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        View view = null;
        if (viewType == ViewHolderType.BRANCH.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_item_view, parent, false);
            viewHolder = new BrachListViewHolder(view);
        } else if (viewType == ViewHolderType.CURRENCY.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_view_item, parent, false);
            viewHolder = new CurrencyListViewHolder(view);
        } else if (viewType == ViewHolderType.BRANCH_HEADER.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_header_view_item, parent, false);
            viewHolder = new BranchListViewHeaderHolder(view);
        } else if (viewType == ViewHolderType.CURRENCY_HEADER.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_header_view, parent, false);
            viewHolder = new CurrencyListViewHeaderHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mListItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mListItems.get(position).getItemType().ordinal();
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    @Override
    public long getItemId(int position) {
        return mListItems.get(position).getItemId();
    }
}
