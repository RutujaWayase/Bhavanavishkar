package com.example.bhavanavishkar;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;

public class MyListView extends AppCompatActivity {


    ListView ListViewOfSongs;

    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_view);

        ListViewOfSongs = (ListView) findViewById(R.id.SongListView);

        runtimePermission();
    }

    public void runtimePermission()
    {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        display();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();

                    }
                })
                .check();
    }



    public ArrayList<File> FindSong(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for(File SingleFile: files)
        {
            if(SingleFile.isDirectory() && !SingleFile.isHidden())
            {
                arrayList.addAll(FindSong(SingleFile));
            }
            else
            {
                if(SingleFile.getName().endsWith(".mp3"))
                {
                    SingleFile.getName().endsWith(".wav");
                }
                arrayList.add(SingleFile);
            }
        } return arrayList;
    }

    void display()
    {
        final ArrayList<File> MySongs = FindSong (Environment.getExternalStorageDirectory());


        items = new String[MySongs.size()];

        for(int i = 0; i < MySongs.size(); i++)
        {
            items[i] = MySongs.get(i).getName().toString().replace(".mp3", "") .replace(".wav", "");
        }

        ArrayAdapter<String> MyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        ListViewOfSongs.setAdapter(MyAdapter);
    }

}