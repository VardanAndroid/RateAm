package am.rate.com.rateamtest.view;

import java.util.List;

import am.rate.com.rateamtest.view.adapter.model.BankAdapterModel;
import am.rate.com.rateamtest.view.model.Bank;

public interface BankListView extends BaseView {

    void setupCashItemListener();

    void setupCurrencyItemListener(List<String> keys);

    void notifyBankList(List<BankAdapterModel> banks);

    void notifyAboutSelectedBank(Bank bank);

}
