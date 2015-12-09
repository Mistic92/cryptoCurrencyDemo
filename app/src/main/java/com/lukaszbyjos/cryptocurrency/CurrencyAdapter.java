package com.lukaszbyjos.cryptocurrency;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lukasz on 09.12.2015:20:33.
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {


    /**
     * The CurrencyList list.
     */
    private List<CurrencyList.Currency> currencyList;
    private Context mContext;

    public CurrencyAdapter(List<CurrencyList.Currency> currencyList, Context mContext) {
        this.currencyList = currencyList;
        this.mContext = mContext;
    }

    /**
     * On create view holder currency view holder.
     *
     * @param parent   the parent
     * @param viewType the view type
     * @return the currency view holder
     */
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_item, parent, false);
        return new CurrencyViewHolder(v);
    }

    /**
     * On bind view holder.
     *
     * @param holder   the holder
     * @param position the position
     */
    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        final CurrencyList.Currency currency = currencyList.get(position);

        holder.currencyAid.setText(mContext.getString(R.string.aid, String.format("%.3f", currency.getCurrencyAsk())));
        holder.currencyBid.setText(mContext.getString(R.string.bid, String.format("%.3f", currency.getCurrencyBid())));
        switch (currency.getCurrencyName()) {
            case "bitcoin":
                Glide.with(mContext).load(R.drawable.bitcoin).into(holder.currencyImage);
                break;
            case "dogecoin":
                Glide.with(mContext).load(R.drawable.dogcoin).into(holder.currencyImage);
                break;
            case "litecoin":
                Glide.with(mContext).load(R.drawable.litecoin).into(holder.currencyImage);
                break;
        }
        holder.currencyName.setText(currency.getCurrencyName());
        holder.currencyValue.setText(mContext.getString(R.string.cValue, String.format("%.6f", currency.getCurrencyValue())));
    }


    /**
     * Gets item count.
     *
     * @return the item count
     */
    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'currency_item.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        /**
         * The CurrencyList image.
         */
        @Bind(R.id.currencyImage)
        ImageView currencyImage;
        /**
         * The CurrencyList name.
         */
        @Bind(R.id.currencyName)
        TextView currencyName;
        /**
         * The CurrencyList value.
         */
        @Bind(R.id.currencyValue)
        TextView currencyValue;
        /**
         * The CurrencyList bid.
         */
        @Bind(R.id.currencyBid)
        TextView currencyBid;
        /**
         * The CurrencyList aid.
         */
        @Bind(R.id.currencyAsk)
        TextView currencyAid;

        /**
         * Instantiates a new CurrencyList view holder.
         *
         * @param view the view
         */
        CurrencyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
