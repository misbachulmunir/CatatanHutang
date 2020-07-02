package com.kubangkangkung.catatan;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RealmHelper {
    private Context context;
    private Realm realm;

    public RealmHelper(Context context) {
        this.context = context;
        Realm.init(context);
        realm=Realm.getDefaultInstance();
    }
    //insert data
    public void insertData(ModelCatatan catatan){
        realm.beginTransaction();
        realm.copyToRealm(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }

    //menampilkan data
    public List<ModelCatatan> showData(){
        RealmResults<ModelCatatan> datahasil=realm.where(ModelCatatan.class).findAll();
        List<ModelCatatan>datalList=new ArrayList<>();
        datalList.addAll(realm.copyFromRealm(datahasil));
        return datalList;
    }

    //show satu data
    public ModelCatatan showOnedata(int id){
        ModelCatatan data=realm.where(ModelCatatan.class).equalTo("id",id).findFirst();
        return data;
    }
    //mendapatkan id
    public  long getNextId(){
        if(realm.where(ModelCatatan.class).count()!=0){
            long id=realm.where(ModelCatatan.class).max("id").longValue();
            return id +1;
        }else {
            return 1;
        }
    }

    //update data
    public void updateData(ModelCatatan catatan){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(catatan);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
            }
        }

        );

    }

    //delete data
    public  void deleteData(Integer id){
        realm.beginTransaction();
        ModelCatatan catatan=realm.where(ModelCatatan.class).equalTo("id",id).findFirst();
        catatan.deleteFromRealm();
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
