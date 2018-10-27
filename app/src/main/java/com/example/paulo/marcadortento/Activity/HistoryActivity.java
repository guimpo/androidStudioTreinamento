package com.example.paulo.marcadortento.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.paulo.marcadortento.Adapters.HistoryAdapter;
import com.example.paulo.marcadortento.Interface.AdapterPositionOnClickListener;
import com.example.paulo.marcadortento.Models.History;
import com.example.paulo.marcadortento.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements AdapterPositionOnClickListener {

    //Criar a variavel da toolbar
    private Toolbar mToolbar;

    private List<History> mList = new ArrayList<>();
    private HistoryAdapter mAdapter;
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        // toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        if (getIntent().hasExtra("history")) {
            mList = (List<History>) getIntent().getSerializableExtra("history");
        }

        mRecycler = findViewById(R.id.rv_history);

        //Validar se a lista esta vazia
        //Exibir mensagem
        if (mList == null || mList.isEmpty()) {
            findViewById(R.id.lbl_message).setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        } else {
            findViewById(R.id.lbl_message).setVisibility(View.INVISIBLE);
            mRecycler.setVisibility(View.VISIBLE);
        }


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        mAdapter = new HistoryAdapter(this, mList);
        mAdapter.setAdapterPositionOnClickListener(this);
        mRecycler.setAdapter(mAdapter);
    }

    //Funcão de clicks da toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    //Função criada que vai receber os dados passados pelo adapter após ter feito a função do click
    @Override
    public void setAdapterPositionOnClickListener(View view, int position) {

        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mAdapter.deleteItem(position);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                return;
            }
        });

        builder.show();
    }
}
