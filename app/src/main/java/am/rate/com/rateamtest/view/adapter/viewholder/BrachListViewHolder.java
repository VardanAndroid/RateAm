package am.rate.com.rateamtest.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.view.adapter.model.BranchAdapterModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BrachListViewHolder extends BaseViewHolder<BranchAdapterModel> {

    @BindView(R.id.branchName)
    TextView mBranchNameTv;

    public BrachListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BranchAdapterModel branchAdapterModel) {
        mBranchNameTv.setText(branchAdapterModel.getName());
    }
}
