package com.erdemtsynduev.project_currencytracker.screen.listcurrency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdemtsynduev.project_currencytracker.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class ListCurrencyFragment extends Fragment implements ListCurrencyContract.View {

    private ListCurrencyContract.Presenter mPresenter;

    public static ListCurrencyFragment newInstance() {
        return new ListCurrencyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listcurrency, container, false);

        return view;
    }


    @Override
    public void setPresenter(@NonNull ListCurrencyContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
