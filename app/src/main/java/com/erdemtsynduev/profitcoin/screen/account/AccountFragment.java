package com.erdemtsynduev.profitcoin.screen.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.erdemtsynduev.profitcoin.R;
import com.erdemtsynduev.profitcoin.screen.about.AboutActivity;
import com.erdemtsynduev.profitcoin.screen.help.HelpActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountFragment extends MvpAppCompatFragment implements AccountView {

    @InjectPresenter
    AccountPresenter mAccountPresenter;

    @BindView(R.id.frame_about_program)
    LinearLayout mLinearLayoutAbout;
    @BindView(R.id.frame_help)
    LinearLayout mLinearLayoutHelp;
    @BindView(R.id.frame_add_api_key)
    LinearLayout mLinearLayoutAddApiKey;

    private DialogPlus dialogPlus;

    public static AccountFragment getInstance() {
        return new AccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mLinearLayoutAbout.setOnClickListener(v -> mAccountPresenter.openScreenAboutApp());
        mLinearLayoutHelp.setOnClickListener(v -> mAccountPresenter.openScreenHelp());
        mLinearLayoutAddApiKey.setOnClickListener(v -> mAccountPresenter.onAddApiDialogOpen());
    }

    @Override
    public void openScreenHelp() {
        Intent intent = new Intent(getContext(), HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public void openScreenAboutApp() {
        Intent intent = new Intent(getContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAddApiDialog() {
        if (getActivity() == null) {
            return;
        }

        dialogPlus = DialogPlus
                .newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_add_api_key))
                .setCancelable(false)
                .setGravity(Gravity.BOTTOM)
                .create();

        EditText editTextApiKey = (EditText) dialogPlus.findViewById(R.id.editText);
        Button btnAccept = (Button) dialogPlus.findViewById(R.id.btn_accept);
        Button btnClose = (Button) dialogPlus.findViewById(R.id.btn_close);

        btnClose.setOnClickListener(v -> mAccountPresenter.onAddApiDialogCancel());
        btnAccept.setOnClickListener(v -> mAccountPresenter.onAddApiDialogAccept(editTextApiKey.getText().toString()));

        dialogPlus.show();
    }

    @Override
    public void hideAddApiDialog() {
        if (dialogPlus != null && dialogPlus.isShowing()) {
            dialogPlus.dismiss();
        }
    }
}
