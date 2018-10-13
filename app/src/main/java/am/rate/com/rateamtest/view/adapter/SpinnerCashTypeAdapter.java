package am.rate.com.rateamtest.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import am.rate.com.rateamtest.R;

public class SpinnerCashTypeAdapter extends BaseAdapter {

    private List<String> mData;
    LayoutInflater inflter;

    public SpinnerCashTypeAdapter(Context context, List<String> data) {
        this.mData = data;
        inflter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.cash_type_spinner_view_item, null);
        TextView names = view.findViewById(R.id.cashTypeName);
        names.setText(mData.get(i));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.cash_type_spinner_view_item, null);
        TextView names = convertView.findViewById(R.id.cashTypeName);
        names.setText(mData.get(position));
        return convertView;
    }
}
