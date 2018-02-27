package com.erdemtsynduev.cryptocurrencycharts.screen.portfolio;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdemtsynduev.cryptocurrencycharts.R;

import static com.erdemtsynduev.cryptocurrencycharts.utils.Utils.checkNotNull;

public class PortfolioFragment extends Fragment implements PortfolioContract.View {

    private PortfolioContract.Presenter mPresenter;

    public static PortfolioFragment newInstance() {
        return new PortfolioFragment();
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
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        return view;
    }


    @Override
    public void setPresenter(@NonNull PortfolioContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
