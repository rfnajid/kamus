package com.nnn.kamus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.nnn.kamus.R;
import com.nnn.kamus.activity.DetailActivity;
import com.nnn.kamus.model.Word;
import com.nnn.kamus.util.S;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ridhaaaaazis on 26/01/18.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    List<Word> wordList;

    public ItemAdapter(List<Word> wordList) {
        this.wordList = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();

        View itemLayoutView = LayoutInflater.from(ctx)
                .inflate(R.layout.item, null);

        ViewHolder viewHolder = new ViewHolder(ctx,itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(wordList.get(position));
    }

    @Override
    public int getItemCount() {
       return wordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Context ctx;
        Word word;

        @BindView(R.id.text)
        TextView textView;


        public ViewHolder(Context ctx, View view) {
            super(view);
            this.ctx = ctx;
            ButterKnife.bind(this,view);
        }

        public void setData(Word word){
            this.word=word;
            textView.setText(word.getOrigin());
        }

        @OnClick(R.id.btn)
        public void intentCall(){
            S.goTo(ctx, DetailActivity.class,word);
        }

    }


}
