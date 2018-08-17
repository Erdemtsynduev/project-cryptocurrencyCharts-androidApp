package com.erdemtsynduev.profitcoin.screen.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface LoginView extends MvpView {

    void showErrorEnterEmail();

    void showErrorPassword();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenSignUp();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openScreenResetPassword();

}