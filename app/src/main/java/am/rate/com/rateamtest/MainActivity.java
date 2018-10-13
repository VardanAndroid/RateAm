package am.rate.com.rateamtest;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import am.rate.com.rateamtest.di.component.ApplicationComponent;
import am.rate.com.rateamtest.presenter.BankListPresneter;
import am.rate.com.rateamtest.rest.BankApi;
import am.rate.com.rateamtest.view.RateApp;
import am.rate.com.rateamtest.view.fragments.BankDetailsFragment;
import am.rate.com.rateamtest.view.fragments.BankListFragment;
import am.rate.com.rateamtest.view.fragments.BaseFragment;
import am.rate.com.rateamtest.view.model.Bank;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements BankListFragment.OnFragmentInteractionListener, BankDetailsFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBankListFragment();
    }

    private void addBankListFragment() {
        BaseFragment fragment = new BankListFragment();
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentFrame, fragment, BankListFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ApplicationComponent getAppComponent() {
        return ((RateApp) getApplication()).getApplicationComponent();
    }

    @Override
    public void openBankDetailsFragment(Bank bank) {
        BaseFragment fragment = BankDetailsFragment.newInstance(bank);
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentFrame, fragment, BankDetailsFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }

    @Override
    public void onTopBackPressed() {
        onBackPressed();
    }
}
