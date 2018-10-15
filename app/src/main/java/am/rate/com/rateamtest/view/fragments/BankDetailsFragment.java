package am.rate.com.rateamtest.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.di.component.ApplicationComponent;
import am.rate.com.rateamtest.presenter.BankDetailsPresenter;
import am.rate.com.rateamtest.presenter.BasePresenter;
import am.rate.com.rateamtest.view.BankDetailsView;
import am.rate.com.rateamtest.view.adapter.BranchListAdapter;
import am.rate.com.rateamtest.view.adapter.model.ListItem;
import am.rate.com.rateamtest.view.model.Bank;
import am.rate.com.rateamtest.view.model.Branch;
import am.rate.com.rateamtest.view.model.Currency;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BankDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BankDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankDetailsFragment extends BaseFragment<BankDetailsView> implements BankDetailsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private static final String BANK_ID = "bank_id";
    private static final String CURRENCY_DATA = "currency_data";
    private static final String BANK_DATA = "bank_data";

    @BindView(R.id.bankName)
    TextView mBankNameTv;
    @BindView(R.id.bankTitle)
    TextView mBankTitleTv;
    @BindView(R.id.bankAddress)
    TextView mBankAddressTv;
    @BindView(R.id.bankContacts)
    TextView mBankContactTv;
    @BindView(R.id.working_hour)
    TextView mBankWorkingOursTv;
    @BindView(R.id.head_of_branch_container)
    ViewGroup mHeadOfBranch;
    @BindView(R.id.cashRbtn)
    RadioButton mRadioButtonCash;
    @BindView(R.id.nonCashRbtn)
    RadioButton mRadioButtonNonCash;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.collapsing_toolbar_appbarlayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.branches_recyclerView)
    RecyclerView mBranchesRecyclerView;
    @BindView(R.id.title)
    TextView mToolbarTitle;

    private BranchListAdapter mAdapter;
    private Bank mBank;


    @Inject
    BankDetailsPresenter mPresenter;

    public BankDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BankDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BankDetailsFragment newInstance(String param1, String param2) {
        BankDetailsFragment fragment = new BankDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static BankDetailsFragment newInstance(String param1, HashMap<String, Currency> currencies) {
        BankDetailsFragment fragment = new BankDetailsFragment();
        Bundle args = new Bundle();
        args.putString(BANK_ID, param1);
        args.putSerializable(CURRENCY_DATA, currencies);
        fragment.setArguments(args);
        return fragment;
    }

    public static BankDetailsFragment newInstance(Bank bank) {
        BankDetailsFragment fragment = new BankDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BANK_DATA, bank);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected BasePresenter<BankDetailsView> getPresenter() {
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener.getAppComponent().inject(this);
        if (getArguments() != null) {
            mBank = (Bank) getArguments().getSerializable(BANK_DATA);
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_bank_details;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupCollapsingToolbarLayout();
        mPresenter.attachView(this);
        mAdapter = new BranchListAdapter();
        mAdapter.setHasStableIds(true);
        mBranchesRecyclerView.setAdapter(mAdapter);
        mPresenter.handleFilter(true, mBank.getCurrencyMap());

        mPresenter.getBankDetails(mBank.getId());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void notifyBranches(List<ListItem> branches) {
        mAdapter.notifyChange(branches);
    }

    @Override
    public void notifyHeadofBranch(Branch branch) {
        mBankTitleTv.setText(branch.getTitle().getTitleEn());
        mBankAddressTv.setText(branch.getAddress().getAddressEn());
        mBankContactTv.setText(branch.getContact());
        mBankWorkingOursTv.setText(branch.getWorkingHours());
        mBankNameTv.setText(mBank.getName());
    }

    @Override
    public void notifyAboutEmptyHeadBranch() {
        mHeadOfBranch.setVisibility(View.GONE);
    }

    @Override
    public void notifyCurrencyChange(List<ListItem> modelList) {
        mAdapter.notifyCurrencyChanged(modelList);
    }

    private void setEnabledActionBar() {
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        if (activity == null)
            return;
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupCollapsingToolbarLayout() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle("");
                    mToolbarTitle.setText(mBank.getName());
                    isShow = true;
                } else if (isShow) {
                    mToolbarTitle.setText("");
                    mCollapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

        ApplicationComponent getAppComponent();

        void onTopBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mListener != null) {
                    mListener.onTopBackPressed();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.cashRbtn)
    void onCashClicked() {
        mPresenter.handleFilter(true, mBank.getCurrencyMap());
        mRadioButtonCash.setChecked(true);
        mRadioButtonNonCash.setChecked(false);
    }

    @OnClick(R.id.nonCashRbtn)
    void onNonCashClicked() {
        mPresenter.handleFilter(false, mBank.getCurrencyMap());
        mRadioButtonCash.setChecked(false);
        mRadioButtonNonCash.setChecked(true);
    }
}
