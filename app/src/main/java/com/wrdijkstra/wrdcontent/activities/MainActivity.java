package com.wrdijkstra.wrdcontent.activities;

import com.wrdijkstra.wrdcontent.controllers.WrdContentContract;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wrdijkstra.wrdcontent.R;
import com.wrdijkstra.wrdcontent.daos.CounterDao;
import com.wrdijkstra.wrdcontent.vos.CounterVo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ContentResolver resolver = getContentResolver();
//        Cursor cursor  = resolver.query(WrdContentContract.CONTENT_URI, null, null, null, WrdContentContract.Counters._ID+" ASC" );

        CounterVo  counterVo = new CounterVo();
        CounterDao counterDao = new CounterDao(getApplicationContext());
        ArrayList<CounterVo> counterVoArrayList = new ArrayList<>();

        counterVo.setId(13);
        counterVo.setLocked(true);
        counterVo.setCount(12);
        counterVo.setLabel("huh?");
        counterDao.insert(counterVo);


        counterVo.setId(11);
        counterVo.setLocked(false);
        counterVo.setCount(1);
        counterVo.setLabel("tja...");
        counterDao.insert(counterVo);

        counterVoArrayList = counterDao.getAll();

        counterVo.setId(15);
    }
}
