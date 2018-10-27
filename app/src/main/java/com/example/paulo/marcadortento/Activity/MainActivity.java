package com.example.paulo.marcadortento.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.paulo.marcadortento.Models.History;
import com.example.paulo.marcadortento.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView lbl_points_t1;
    private ImageButton btn_plus_t1;
    private ImageButton btn_less_t1;
    private int pnts_t1 = 0;

    private TextView lbl_points_t2;
    private ImageButton btn_plus_t2;
    private ImageButton btn_less_t2;
    private int pnts_t2 = 0;

    private TextView mBtnHistory;
    private ArrayList<History> mHistory = new ArrayList<>();

    private View.OnClickListener plusClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_plus_time1:
                    ++pnts_t1;
                    break;

                case R.id.btn_plus_time2:
                    ++pnts_t2;
                    break;
            }

            if (pnts_t1 == 12 || pnts_t2 == 12) {
                History h = new History(pnts_t1, pnts_t2);
                mHistory.add(h);
                pnts_t1 = 0;
                pnts_t2 = 0;
            }
            lbl_points_t1.setText(String.valueOf(pnts_t1));
            lbl_points_t2.setText(String.valueOf(pnts_t2));
        }
    };

    private View.OnClickListener lessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_less_time1:
                    if (pnts_t1 > 0) --pnts_t1;
                    break;

                case R.id.btn_less_time2:
                    if (pnts_t2 > 0) --pnts_t2;
                    break;
            }
            lbl_points_t1.setText(String.valueOf(pnts_t1));
            lbl_points_t2.setText(String.valueOf(pnts_t2));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {
        setContentView(R.layout.activity_main);

        lbl_points_t1 = findViewById(R.id.lbl_pontos_time1);
        btn_plus_t1 = findViewById(R.id.btn_plus_time1);
        btn_plus_t1.setOnClickListener(plusClick);
        btn_less_t1 = findViewById(R.id.btn_less_time1);
        btn_less_t1.setOnClickListener(lessClick);

        lbl_points_t2 = findViewById(R.id.lbl_pontos_time2);
        btn_plus_t2 = findViewById(R.id.btn_plus_time2);
        btn_plus_t2.setOnClickListener(plusClick);
        btn_less_t2 = findViewById(R.id.btn_less_time2);
        btn_less_t2.setOnClickListener(lessClick);

        mBtnHistory = findViewById(R.id.btn_historico);
        mBtnHistory.setOnClickListener(historyListener);
    }

    private View.OnClickListener historyListener = (View v) -> {
        startActivity(new Intent(MainActivity.this, HistoryActivity.class)
                .putExtra("history", mHistory));
    };
}
