package am.rate.com.rateamtest.rest;

import java.util.Map;

import am.rate.com.rateamtest.model.entity.BankDetailsEntity;
import am.rate.com.rateamtest.model.entity.BankEntity;
import am.rate.com.rateamtest.model.entity.BranchEntity;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BankApi {

    String GET_BANK_LIST_URL = "rates.ashx?lang=en";
    String GET_BANK_DETAILS = "branches.ashx?";
    String ORGANIZATION_ID = "id";

    @GET("rates.ashx?lang=en")
    Single<Map<String, BankEntity>> getBanks();

    @GET(GET_BANK_DETAILS)
    Single<BankDetailsEntity> getBankDetails(@Query(ORGANIZATION_ID) String id);

}
