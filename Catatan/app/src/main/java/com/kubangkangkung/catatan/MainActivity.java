package com.kubangkangkung.catatan;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<ModelCatatan>catatansaya=new ArrayList<>();
RecyclerView recyclerView;
RealmHelper realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.rcviewdepan);
        realm=new RealmHelper(MainActivity.this);

//        //buat data dummy
//        ModelCatatan catatan1=new ModelCatatan();
//        catatan1.setId(1);
//        catatan1.setJudul("Pak Usman");
//        catatan1.setJumlah("400000");
//        catatan1.setTanggal("22-09-2020");
//        for (int i = 0; i < 20; i++) {
//            catatansaya.add(catatan1);
//        }

        catatansaya=realm.showData();

        recyclerView.setAdapter(new AdapterCatatan(MainActivity.this,catatansaya));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,1));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,TambahActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        catatansaya=realm.showData();
        recyclerView.setAdapter(new AdapterCatatan(MainActivity.this,catatansaya));
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,1));
    }
}