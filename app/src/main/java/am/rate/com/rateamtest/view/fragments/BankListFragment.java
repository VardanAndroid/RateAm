package am.rate.com.rateamtest.view.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import am.rate.com.rateamtest.R;
import am.rate.com.rateamtest.di.component.ApplicationComponent;
import am.rate.com.rateamtest.presenter.BankListPresneter;
import am.rate.com.rateamtest.presenter.BasePresenter;
import am.rate.com.rateamtest.view.BankListView;
import am.rate.com.rateamtest.view.adapter.BankListAdapter;
import am.rate.com.rateamtest.view.adapter.OnBankItemClickListener;
import am.rate.com.rateamtest.view.adapter.SpinnerCashTypeAdapter;
import am.rate.com.rateamtest.view.adapter.SpinnerCurrencyAdapter;
import am.rate.com.rateamtest.view.adapter.model.BankAdapterModel;
import am.rate.com.rateamtest.view.model.Bank;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BankListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BankListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BankListFragment extends BaseFragment<BankListView> implements BankListView, OnBankItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.cashType)
    Spinner mCashTypesSpinner;
    @BindView(R.id.currencyType)
    Spinner mCurrencySpinner;
    @BindView(R.id.banksRecyclerView)
    RecyclerView mBanksRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private BankListAdapter mAdapter;
    private Spinner.OnItemSelectedListener mCashItemSelectedListener;
    private Spinner.OnItemSelectedListener mCurrencyItemSelectedListener;

    private String mSelectedCurrency = DEFAULT_KEY;
    private boolean isCash = true;

    private static final String DEFAULT_KEY = "USD";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    @Inject
    BankListPresneter mPresneter;

    public BankListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BankListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BankListFragment newInstance(String param1, String param2) {
        BankListFragment fragment = new BankListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener.getAppComponent().inject(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_bank_list, container, false);
    }

    @Override
    protected BasePresenter<BankListView> getPresenter() {
        return mPresneter;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setTitleTextColor(Color.WHITE);
        mPresneter.attachView(this);
        mAdapter = new BankListAdapter(this, getActivity());
        mAdapter.setHasStableIds(true);
        mBanksRecyclerView.setAdapter(mAdapter);
        mPresneter.getBanks();
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_bank_list;
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setupCashItemListener() {
        List<String> categories = new ArrayList<String>();
        categories.add("Cash");
        categories.add("Non Cash");
        SpinnerCashTypeAdapter adapter = new SpinnerCashTypeAdapter(getActivity(), categories);
        mCashTypesSpinner.setAdapter(adapter);
        mCashItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        isCash = true;
                        break;
                    case 1:
                        isCash = false;
                        break;
                }
                mPresneter.handleFilter(isCash, mSelectedCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        mCashTypesSpinner.setSelection(0, false);
        mCashTypesSpinner.setOnItemSelectedListener(mCashItemSelectedListener);
    }

    @Override
    public void setupCurrencyItemListener(List<String> keys) {
        SpinnerCurrencyAdapter adapter = new SpinnerCurrencyAdapter(getActivity(), keys);
        mCurrencySpinner.setAdapter(adapter);
        mCurrencyItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedCurrency = adapter.getItem(i);
                mPresneter.handleFilter(isCash, mSelectedCurrency);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        mCurrencySpinner.setOnItemSelectedListener(mCurrencyItemSelectedListener);

    }

    @Override
    public void notifyBankList(List<BankAdapterModel> banks) {
        mAdapter.notifyChange(banks);
    }

    @Override
    public void onBankClicked(BankAdapterModel bank) {
        mPresneter.findSelectedBank(bank.getId());
    }

    @Override
    public void notifyAboutSelectedBank(Bank bank) {
        if (mListener != null) {
            mListener.openBankDetailsFragment(bank);
        }
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

        void openBankDetailsFragment(Bank bank);
    }
}
