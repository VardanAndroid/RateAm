package am.rate.com.rateamtest.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.presenter.BasePresenter;
import am.rate.com.rateamtest.view.BaseView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView> extends Fragment implements BaseView {

    private Unbinder mUnbinder;

    private BasePresenter mBasePresenter;
    private FrameLayout mBaseView;
    private ProgressBar mLoader;


    protected abstract BasePresenter<V> getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBaseView = view.findViewById(R.id.baseView);
        mLoader = view.findViewById(R.id.loader);
        mBasePresenter = getPresenter();
        int resourceId = getResourceId();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View childView = inflater.inflate(resourceId, null);
        mUnbinder = ButterKnife.bind(this, childView);
        mBaseView.addView(childView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mBasePresenter != null)
            mBasePresenter.onDestroy();
    }

    protected abstract int getResourceId();

    @Override
    public void showError(String msg) {
        if (getContext() != null && isAdded())
            mBasePresenter.showAlert(msg, getContext());
    }

    @Override
    public void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }
}
