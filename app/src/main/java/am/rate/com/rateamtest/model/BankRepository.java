package am.rate.com.rateamtest.model;

import java.util.List;

import javax.inject.Inject;

import am.rate.com.rateamtest.model.mapper.BankDataMapper;
import am.rate.com.rateamtest.rest.BankApi;
import am.rate.com.rateamtest.view.model.Bank;
import am.rate.com.rateamtest.view.model.Branch;
import io.reactivex.Single;

public class BankRepository {

    private BankApi mBankApi;
    private BankDataMapper mDataMapper;

    @Inject
    public BankRepository(BankDataMapper mapper, BankApi bankApi) {
        mBankApi = bankApi;
        mDataMapper = mapper;
    }

    public Single<List<Bank>> getBankList() {
        return mBankApi.getBanks()
                .map(res -> mDataMapper.getBanks(res));
    }


    public Single<List<Branch>> getBankDetails(String id) {
        return mBankApi.getBankDetails(id)
                .map(res -> mDataMapper.getBranches(res.getBranchEntityMap()));
    }


}
