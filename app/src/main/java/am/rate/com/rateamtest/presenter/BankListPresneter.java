package am.rate.com.rateamtest.presenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import am.rate.com.rateamtest.model.BankRepository;
import am.rate.com.rateamtest.view.BankListView;
import am.rate.com.rateamtest.view.adapter.model.BankAdapterModel;
import am.rate.com.rateamtest.view.model.Bank;
import am.rate.com.rateamtest.view.model.Currency;
import am.rate.com.rateamtest.view.model.Rate;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BankListPresneter extends BasePresenter {

    private BankRepository mRepository;
    private List<Bank> mBanks;

    @Inject
    public BankListPresneter(BankRepository repository) {
        super();
        mRepository = repository;
    }

    private boolean needToLoadBanks() {
        return mBanks == null || mBanks.size() == 0;
    }

    public void getBanks() {
        if (needToLoadBanks()) {
            showLoader();
            mDisposable = new CompositeDisposable();
            Disposable disposable = mRepository.getBankList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(banks -> {
                        hideLoader();
                        mBanks = banks;
                        if (mView != null) {
                            ((BankListView) mView).setupCashItemListener();
                        }
                        filterCurrencyTypes();
                    }, throwable -> {
                        //
                        hideLoader();
                        if (throwable instanceof IOException) {
                            showError("No Internet connection");
                        } else {
                            showError("Something went wrong");
                        }

                    });
            mDisposable.add(disposable);
        } else {
            if (mView != null) {
                ((BankListView) mView).setupCashItemListener();
            }
            filterCurrencyTypes();
        }
    }

    private void showError(String msg) {
        if (mView != null) {
            mView.showError(msg);
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

    @Override
    public void onPause() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private List<BankAdapterModel> getAllCurrency(boolean isCash, String key) {
        List<BankAdapterModel> list = new ArrayList<>();
        for (Bank bank : mBanks) {
            if (bank.hasRateByCashType(isCash, key)) {
                Map<String, Currency> currencyMap = bank.getCurrencyMap();
                Currency currency = currencyMap.get(key);
                Rate rate = isCash ? currency.getCash() : currency.getNonCash();

                list.add(new BankAdapterModel(bank.getId(), bank.getName(), rate));
            }
        }
        findMaxSell(list);
        findMaxBuy(list);
        return list;
    }

    private void findMaxSell(List<BankAdapterModel> models) {
        float max = -1;
        int maxPos = -1;
        for (int i = 0; i < models.size(); i++) {
            Rate rate = models.get(i).getRate();
            if (rate.getSell() > max) {
                max = rate.getSell();
                maxPos = i;
            }
        }
        if (maxPos != -1) {
            models.get(maxPos).getRate().setMaxSell(true);
        }
    }

    private void findMaxBuy(List<BankAdapterModel> models) {
        float max = -1;
        int maxPos = -1;
        for (int i = 0; i < models.size(); i++) {
            Rate rate = models.get(i).getRate();
            if (rate.getBuy() > max) {
                max = rate.getBuy();
                maxPos = i;
            }
        }
        if (maxPos != -1) {
            models.get(maxPos).getRate().setMaxBuy(true);
        }
    }


    public void handleFilter(boolean isCash, String key) {
        List<BankAdapterModel> banks = getAllCurrency(isCash, key);
        if (mView != null) {
            ((BankListView) mView).notifyBankList(banks);
        }
    }

    private void filterCurrencyTypes() {
        Set<String> types = new HashSet<>();
        for (Bank bank :
                mBanks) {
            Map<String, Currency> map = bank.getCurrencyMap();
            types.addAll(map.keySet());
        }
        List<String> array = new ArrayList<>(types);
        if (mView != null) {
            ((BankListView) mView).setupCurrencyItemListener(array);
        }
    }

    public void findSelectedBank(String id) {
        for (Bank bank : mBanks) {
            if (bank.getId().equals(id)) {
                if (mView != null) {
                    ((BankListView) mView).notifyAboutSelectedBank(bank);
                    break;
                }
            }
        }
    }


}
