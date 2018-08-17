package com.erdemtsynduev.profitcoin.screen.signup;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface SignupView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenResetPassword();

    void showErrorEnterEmail();

    void showErrorPassword();

    void showErrorPasswordSmall();
}
