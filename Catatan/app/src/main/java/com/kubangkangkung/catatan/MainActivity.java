package com.kubangkangkung.catatan;

import android.content.Intent;
import android.os.Bundle;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<ModelCatatan>catatansaya=new ArrayList<>();
RecyclerView recyclerView;
RealmHelper realm;
FloatingSearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.rcviewdepan);
        realm=new RealmHelper(MainActivity.this);
        search=findViewById(R.id.floating_search_view);





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
        //cari data
        search.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                //  Toast.makeText(MainActivity.this, "Hasil query", Toast.LENGTH_SHORT).show();
                //filter searchview
                List<ModelCatatan>filtercatatan=fiterData(catatansaya, newQuery);
                recyclerView.setAdapter(new AdapterCatatan(MainActivity.this,filtercatatan));
            }
        });

        //huruf kapital di awal

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    private void jumlahTotal(){
        List<ModelCatatan>data=new ArrayList<>();
        for (int i = 0; i <data.size() ; i++) {
            int jumlah= Integer.parseInt(data.get(i).getJumlah());
            int hasil=jumlah+jumlah;

        }
    };

    //method pencarian
    private List<ModelCatatan> fiterData(List<ModelCatatan> catatansaya, String newQuery) {
        String lowercase=newQuery.toLowerCase();
        List<ModelCatatan>filterData=new ArrayList<>();
        for (int i = 0; i < catatansaya.size(); i++) {
            String text=catatansaya.get(i).getJudul().toLowerCase();
            String tanggal=catatansaya.get(i).getTanggal().toLowerCase();
            String jumlah=catatansaya.get(i).getJumlah().toLowerCase();
            if(text.contains(lowercase)||tanggal.contains(lowercase)||jumlah.contains(lowercase)){
                filterData.add(catatansaya.get(i));
            }
        }
        return filterData;
    };

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