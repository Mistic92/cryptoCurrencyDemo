package com.lukaszbyjos.cryptocurrency;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The constant TAG.
     */
    private final static String TAG = "CurrencyFun";

    /**
     * The Main recycler.
     */
    @Bind(R.id.mainRecycler)
    RecyclerView mainRecycler;

    /**
     * The Loader layout.
     */
    @Bind(R.id.loaderLayout)
    LinearLayout loaderLayout;

    /**
     * The Currency list.
     */
    private List<CurrencyList.Currency> currencyList = new ArrayList<>();
    /**
     * The Currency adapter.
     */
    private CurrencyAdapter currencyAdapter;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        connectToFirebase();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mainRecycler.setLayoutManager(llm);

        currencyAdapter = new CurrencyAdapter(currencyList, this);
        mainRecycler.setAdapter(currencyAdapter);
    }

    /**
     * Hide loader.
     */
    private void hideLoader() {
        loaderLayout.setVisibility(View.GONE);
    }

    /**
     * Connect to firebase.
     */
    private void connectToFirebase() {
        Firebase myFirebaseRef = new Firebase("https://publicdata-cryptocurrency.firebaseio.com/");
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (loaderLayout.getVisibility() == View.VISIBLE)
                    hideLoader();

                currencyList.clear();

                CurrencyList.Currency currency = new CurrencyList.Currency();
                wrapCurrencyResponse(dataSnapshot, currency, "litecoin");
                currencyList.add(currency);

                currency = new CurrencyList.Currency();
                wrapCurrencyResponse(dataSnapshot, currency, "bitcoin");
                currencyList.add(currency);

                currency = new CurrencyList.Currency();
                wrapCurrencyResponse(dataSnapshot, currency, "dogecoin");
                currencyList.add(currency);

                currencyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    /**
     * Wrap currency response.
     *
     * @param dataSnapshot the data snapshot
     * @param currency     the currency
     * @param currencyName the currency name
     */
    private void wrapCurrencyResponse(DataSnapshot dataSnapshot, CurrencyList.Currency currency, final String currencyName) {
        final String bid = dataSnapshot.child(currencyName).child("bid").getValue().toString();
        final String ack = dataSnapshot.child(currencyName).child("ask").getValue().toString();
        final String last = dataSnapshot.child(currencyName).child("last").getValue().toString();

        currency.setCurrencyAsk(Float.parseFloat(ack));
        currency.setCurrencyBid(Float.parseFloat(bid));
        currency.setCurrencyValue(Float.parseFloat(last));
        currency.setCurrencyName(currencyName);
    }
}
