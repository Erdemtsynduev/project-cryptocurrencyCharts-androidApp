package com.erdemtsynduev.project_currencytracker.listcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.erdemtsynduev.project_currencytracker.R;
import com.erdemtsynduev.project_currencytracker.model.ResponseItem;
import com.erdemtsynduev.project_currencytracker.restclient.RetrofitGetInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ListCurrencyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_currency);
        doRequest();
    }

    private void doRequest(){

    }

    private void arrayListInit(List<ResponseItem> responseItemList){

    }
}
