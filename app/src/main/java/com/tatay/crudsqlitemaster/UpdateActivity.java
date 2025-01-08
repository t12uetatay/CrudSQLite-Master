package com.tatay.crudsqlitemaster;

import android.content.Intent;
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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class UpdateActivity extends AppCompatActivity {
    private TextInputLayout lyNis, lyNama;
    private TextInputEditText inpNis, inpNama;
    private RadioGroup rgJk;
    private MaterialRadioButton rbL, rbP;
    private Spinner spnKk;
    private DatabaseHelper db;
    private Toolbar toolbar;
    private Siswa siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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
        Intent intent = getIntent();
        String msg = intent.getStringExtra("nis");
        siswa = db.readSiswa(msg);
        inpNis.setText(String.valueOf(siswa.getNis()));
        inpNis.setEnabled(false);
        inpNama.setText(siswa.getNamaSiswa());
        if (siswa.getJenisKelamin().equals("Laki-laki")){
            rbL.setChecked(true);
            rbP.setChecked(false);
        } else if (siswa.getJenisKelamin().equals("Perempuan")){
            rbL.setChecked(false);
            rbP.setChecked(true);
        }

        spnKk.setSelection(getIndexKk(siswa.getKonsentrasiKeahlian()));


        findViewById(R.id.btn_simpan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validasiForm()){
                    String jk="-";
                    if (rbL.isChecked()){
                        jk=rbL.getText().toString();
                    } else if (rbP.isChecked()){
                        jk=rbP.getText().toString();
                    }
                    db.updateSiswa(new Siswa(
                            Integer.parseInt(inpNis.getText().toString()),
                            inpNama.getText().toString(),
                            jk,
                            spnKk.getSelectedItem().toString()
                    ));
                    resetForm();
                }
            }
        });

    }

    private Boolean validasiForm(){
        Boolean valid=false;
        if (inpNis.getText().toString().length()==0){
            lyNis.setError("NIS harus di isi!");
            valid = false;
        } else {
            lyNis.setError(null);
            valid = true;
        }

        if (inpNama.getText().toString().length()==0){
            lyNama.setError("Nama harus di isi!");
            valid = false;
        } else {
            lyNama.setError(null);
            valid = true;
        }

        if (!rbL.isChecked() || !rbP.isChecked()){
            Snackbar.make(rgJk, "Jenis kelamin belum dipilih!", BaseTransientBottomBar.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        if (spnKk.getSelectedItemPosition()==0){
            Snackbar.make(spnKk, "Jenis kelamin belum dipilih!", BaseTransientBottomBar.LENGTH_SHORT).show();
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    private void resetForm(){
        inpNis.setText(null);
        inpNama.setText(null);
        rgJk.clearCheck();
        spnKk.setSelection(0);
    }

    private int getIndexKk(String kk){
        int idx=0;
        String[] array = getResources().getStringArray(R.array.pilihan_kk);
        for (int i=0; i<array.length; i++){
            if (kk.equals(array[i])){
                idx=i;
            }
        }
        return idx;
    }
}