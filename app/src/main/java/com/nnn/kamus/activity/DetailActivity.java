package com.nnn.kamus.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.nnn.kamus.R;
import com.nnn.kamus.model.Word;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    Word word;

    @BindView(R.id.text_origin)
    TextView textOrigin;

    @BindView(R.id.text_meaning)
    TextView textMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        Intent i = getIntent();
        word = (Word) i.getSerializableExtra("extra");

        textOrigin.setText(word.getOrigin());
        textMeaning.setText(word.getMeaning());
    }
}
