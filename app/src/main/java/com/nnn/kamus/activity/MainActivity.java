package com.nnn.kamus.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nnn.kamus.R;
import com.nnn.kamus.adapter.ItemAdapter;
import com.nnn.kamus.model.Word;
import com.nnn.kamus.util.S;
import com.nnn.kamus.util.db.DatabaseContract;
import com.nnn.kamus.util.db.Pref;
import com.nnn.kamus.util.db.Preloader;
import com.nnn.kamus.util.db.WordHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    @BindView(R.id.btnSwap)
    ImageButton btnSwap;

    @BindView(R.id.btn2)
    Button btn2;

    @BindView(R.id.edit)
    EditText edit;

    @BindView(R.id.text_no)
    TextView textNo;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    List<Word> wordList;
    ItemAdapter itemAdapter;

    String lang;

    WordHelper wordHelper;

    DataLoader dataLoader=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        wordHelper = new WordHelper(this);
        wordHelper.open();

        preload();

        wordList = new ArrayList<>();
        itemAdapter = new ItemAdapter(wordList);
        LinearLayoutManager llm = new LinearLayoutManager(this);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(llm);

        btn1.setText(DatabaseContract.TABLE_ENGLISH);
        btn2.setText(DatabaseContract.TABLE_INDO);
        lang=btn1.getText().toString();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wordHelper!=null)
            wordHelper.close();
    }

    @OnClick(R.id.btnSwap)
    void swap(){
        String temp=btn2.getText()+"";
        btn2.setText(btn1.getText()+"");
        btn1.setText(temp);
        lang=temp;
        edit.setText(null);

        S.log("btn swap : "+btn2.getText()+"->"+temp);
    }

    @OnTextChanged(value = R.id.edit,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void search(CharSequence q){

        if(dataLoader!=null){
            dataLoader.cancel(true);
        }

        if(TextUtils.isEmpty(q)){
            wordList.clear();
            itemAdapter.notifyDataSetChanged();
            noData(true);
            return;
        }

        dataLoader = new DataLoader();
        dataLoader.execute(q+"");
    }

    void noData(boolean bool){
        textNo.setVisibility(
                bool ? View.VISIBLE : View.INVISIBLE
        );
    }

    void preload(){
        if(!Pref.isOld(this)){
            S.log("IS NEW, NOW PRELOADING DATA");
            List<Word> english  = Preloader.preloadRaw(this,"english_indonesia");
            List<Word> indo     = Preloader.preloadRaw(this,"indonesia_english");

            wordHelper.bulkInsert(DatabaseContract.TABLE_ENGLISH,english);
            wordHelper.bulkInsert(DatabaseContract.TABLE_INDO,indo);

            Pref.setOld(this);
        }
        S.log("Data has been loaded");
    }

    private class DataLoader extends AsyncTask<String, Void, ArrayList<Word>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (wordList.size() > 0){
                wordList.clear();
            }
        }

        @Override
        protected ArrayList<Word> doInBackground(String... strings) {
            return wordHelper.search(lang,strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Word> words) {
            super.onPostExecute(words);
            wordList.addAll(words);
            itemAdapter.notifyDataSetChanged();

            if (wordList.size() == 0){
                noData(true);
            }else{
                noData(false);
            }

            dataLoader=null;
        }
    }
}
