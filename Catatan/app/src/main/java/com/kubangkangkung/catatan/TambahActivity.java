package com.kubangkangkung.catatan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TambahActivity extends AppCompatActivity {
EditText judul,jumlah,tanggal;
Button simpan;
RealmHelper realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inisialisasi
        setContentView(R.layout.activity_tambah);
        realm=new RealmHelper(TambahActivity.this);
        judul=findViewById(R.id.id_judul_tambah);
        jumlah=findViewById(R.id.id_jumlah_tambah);
        tanggal=findViewById(R.id.id_tanggal_tambah);
        simpan=findViewById(R.id.btnTambah);


        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();

                final int tahun=calendar.get(calendar.YEAR);
                final int bulan=calendar.get(calendar.MONTH);
                final int hari=calendar.get(calendar.DATE);


                DatePickerDialog dialog=new DatePickerDialog(TambahActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal=Calendar.getInstance();
                        cal.set(tahun,bulan,hari);

                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        tanggal.setText(dateFormat.format(cal.getTime()));

                    }
                },tahun,bulan,hari);
                dialog.show();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelCatatan catatan=new ModelCatatan();
                catatan.setId((int) realm.getNextId());
                catatan.setJudul(judul.getText().toString());
                catatan.setJumlah(jumlah.getText().toString());
                catatan.setTanggal(tanggal.getText().toString());

                realm.insertData(catatan);
                finish();
            }
        });
    }
}