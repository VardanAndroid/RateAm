package am.rate.com.rateamtest.di.component;

import javax.inject.Singleton;

import am.rate.com.rateamtest.MainActivity;
import am.rate.com.rateamtest.di.module.ApplicationModule;
import am.rate.com.rateamtest.view.fragments.BankDetailsFragment;
import am.rate.com.rateamtest.view.fragments.BankListFragment;
import dagger.Component;
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BankListFragment fragment);

    void inject(BankDetailsFragment fragment);

}
