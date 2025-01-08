package com.tatay.crudsqlitemaster;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CreateActivity extends AppCompatActivity {
    private TextInputLayout lyNis, lyNama;
    private TextInputEditText inpNis, inpNama;
    private RadioGroup rgJk;
    private MaterialRadioButton rbL, rbP;
    private Spinner spnKk;
    private DatabaseHelper db;
    private Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        lyNis = findViewById(R.id.ly_nis);
        lyNama = findViewById(R.id.ly_nama);
        inpNis = findViewById(R.id.inp_nis);
        inpNama = findViewById(R.id.inp_nama);
        rgJk = findViewById(R.id.rg_jk);
        rbL = findViewById(R.id.rb_l);
        rbP = findViewById(R.id.rb_p);
        spnKk = findViewById(R.id.spn_kk);
        db = new DatabaseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_simpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jk="-";
                if (rbL.isChecked()){
                    jk=rbL.getText().toString();
                } else if (rbP.isChecked()){
                    jk=rbP.getText().toString();
                }
                db.createSiswa(new Siswa(
                        Integer.parseInt(inpNis.getText().toString()),
                        inpNama.getText().toString(),
                        jk,
                        spnKk.getSelectedItem().toString()
                ));
                resetForm();
            }
        });
    }

    private void resetForm(){
        inpNis.setText(null);
        inpNama.setText(null);
        rgJk.clearCheck();
        spnKk.setSelection(0);
    }
}