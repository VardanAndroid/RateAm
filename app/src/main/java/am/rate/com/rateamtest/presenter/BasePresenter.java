package am.rate.com.rateamtest.presenter;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.view.BaseView;
import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends BaseView> implements Presenter {

    protected CompositeDisposable mDisposable;
    protected V mView;

    public BasePresenter() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mDisposable.dispose();
        mView = null;
    }

    public void showAlert(String message, Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .setPositiveButton("OK", (dialog, which) -> {
                })
                .show();
        alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));
    }

    public void attachView(V view) {
        mView = view;
    }
}
