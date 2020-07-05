package com.kubangkangkung.catatan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    public static final String KEY_ID ="KEY_ID" ;
    EditText judul,jumlah,tanggal;
    Button update,delete;
    RealmHelper realm;
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm=new RealmHelper(DetailActivity.this);
        setContentView(R.layout.activity_detail);
        judul=findViewById(R.id.id_judul_detail);
        jumlah=findViewById(R.id.id_jumlah_detail);
        tanggal=findViewById(R.id.id_tanggal_detail);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);

        final int dataID =getIntent().getIntExtra(KEY_ID,0);
        ModelCatatan data = realm.showOnedata(dataID);

        judul.setText(data.getJudul());
        jumlah.setText(data.getJumlah());
        tanggal.setText(data.getTanggal());

        Log.d(TAG, "id" +dataID);
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();

                final int tahun=calendar.get(calendar.YEAR);
                final int bulan=calendar.get(calendar.MONTH);
                final int hari=calendar.get(calendar.DATE);


                DatePickerDialog dialog=new DatePickerDialog(DetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar cal=Calendar.getInstance();
                        cal.set(year,month,dayOfMonth);

                        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        tanggal.setText(dateFormat.format(cal.getTime()));

                    }
                },tahun,bulan,hari);
                dialog.show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.deleteData(dataID);
                Toast.makeText(DetailActivity.this, "Data berhasil di hapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ModelCatatan catatan=new ModelCatatan();
            catatan.setId(dataID);
            catatan.setJumlah(jumlah.getText().toString());
            catatan.setJudul(judul.getText().toString());
            catatan.setTanggal(tanggal.getText().toString());
            realm.updateData(catatan);
                Toast.makeText(DetailActivity.this, "Data berhasil di update", Toast.LENGTH_SHORT).show();
            finish();
            }

        });
    }
}