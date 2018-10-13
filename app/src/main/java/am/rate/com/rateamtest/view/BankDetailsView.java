package am.rate.com.rateamtest.view;

import java.util.List;

import am.rate.com.rateamtest.view.adapter.model.BranchAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.CurrencyAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.ListItem;
import am.rate.com.rateamtest.view.model.Branch;

public interface BankDetailsView extends BaseView {

    void notifyBranches(List<ListItem> branches);

    void notifyHeadofBranch(Branch branch);

    void notifyCurrencyChange(List<ListItem> modelList);
}
