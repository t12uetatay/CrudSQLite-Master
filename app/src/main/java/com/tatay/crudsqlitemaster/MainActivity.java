package com.tatay.crudsqlitemaster;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity implements SiswaAdapter.AdapterListener{
    private RecyclerView recyclerView;
    private DatabaseHelper db;
    private SiswaAdapter mAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        db = new DatabaseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });

        onLoadData();
    }

    public void onResume(){
        super.onResume();
        onLoadData();
    }

    private void onLoadData(){
        mAdapter = new SiswaAdapter(MainActivity.this, this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new Space(20, 0));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setDataList(db.readAllSiswa());
    }

    @Override
    public void onClick(Siswa siswa) {
        Toast.makeText(getApplicationContext(), siswa.getNamaSiwa(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreMenu(Siswa siswa, ImageView widget) {
        showActionsDialog(siswa);
    }

    private void actionDelete(Siswa siswa){
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle("Konfirmasi!")
                .setMessage("Anda yakin ingin menghapus"+siswa.getNamaSiwa()+"?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteSiswa(siswa.getNis());
                        onLoadData();
                    }
                })
                .setNeutralButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private void showActionsDialog(Siswa siswa) {
        CharSequence[] items = {"Edit", "Hapus"};
        new MaterialAlertDialogBuilder(MainActivity.this)
                .setTitle(siswa.getNamaSiwa())
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                            i.putExtra("nis", String.valueOf(siswa.getNis()));
                            startActivity(i);
                        } else {
                            actionDelete(siswa);
                        }
                    }
                }).show();
    }
}