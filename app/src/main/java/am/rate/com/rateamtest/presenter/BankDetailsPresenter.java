package am.rate.com.rateamtest.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import am.rate.com.rateamtest.model.BankRepository;
import am.rate.com.rateamtest.view.BankDetailsView;
import am.rate.com.rateamtest.view.adapter.model.BranchAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.BranchHeaderAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.CurrencyAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.CurrencyHeaderAdapterModel;
import am.rate.com.rateamtest.view.adapter.model.ListItem;
import am.rate.com.rateamtest.view.model.Branch;
import am.rate.com.rateamtest.view.model.Currency;
import am.rate.com.rateamtest.view.model.Rate;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BankDetailsPresenter extends BasePresenter {

    private BankRepository mRepository;

    @Inject
    public BankDetailsPresenter(BankRepository repository) {
        super();
        mRepository = repository;
    }

    public void getBankDetails(String id) {
        showLoader();
        mDisposable = new CompositeDisposable();
        Disposable disposable = mRepository.getBankDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(branches -> {
                    hideLoader();
                    convertToBranchAdapterModel(branches);
                }, throwable -> {
                    if (throwable instanceof IOException) {
                        showError("No Internet connection");
                    } else {
                        showError("Something went wrong");
                    }
                });
        mDisposable.add(disposable);
    }

    private void showError(String msg) {
        if (mView != null) {
            mView.showError(msg);
        }
    }

    private void convertToBranchAdapterModel(List<Branch> branches) {
        List<ListItem> list = new ArrayList<>();
        BranchHeaderAdapterModel header = new BranchHeaderAdapterModel();
        list.add(header);
        for (Branch branch : branches) {
            list.add(new BranchAdapterModel(branch.getTitle().getTitleEn(), branch.getId()));
        }
        if (mView != null) {
            ((BankDetailsView) mView).notifyBranches(list);
        }
        getHeadBranch(branches);
    }

    private void getHeadBranch(List<Branch> branches) {
        boolean hasHead = false;
        for (Branch branch : branches) {
            if (branch.isHead()) {
                if (mView != null) {
                    ((BankDetailsView) mView).notifyHeadofBranch(branch);
                }
                hasHead = true;
            }
        }
        if (!hasHead) {
            if (mView != null) {
                ((BankDetailsView) mView).notifyHeadofBranch(branches.get(0));
            }
        }
    }

    public void handleFilter(boolean isCash, Map<String, Currency> currencyMap) {
        List<ListItem> modelList = new ArrayList<>();
        CurrencyHeaderAdapterModel header = new CurrencyHeaderAdapterModel();
        modelList.add(header);
        Set<String> keySet = currencyMap.keySet();
        for (String keyName : keySet) {
            Currency currency = currencyMap.get(keyName);
            if (currency.hasCurrency(isCash)) {
                Rate rate = isCash ? currency.getCash() : currency.getNonCash();
                CurrencyAdapterModel model = new CurrencyAdapterModel(keyName, rate);
                modelList.add(model);
            }
        }

        if (mView != null) {
            ((BankDetailsView) mView).notifyCurrencyChange(modelList);
        }
    }


    private void showLoader() {
        if (mView != null) {
            mView.showLoader();
        }
    }

    private void hideLoader() {
        if (mView != null) {
            mView.hideLoader();
        }
    }
}
