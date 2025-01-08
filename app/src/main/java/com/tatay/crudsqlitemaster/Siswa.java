package com.tatay.crudsqlitemaster;

public class Siswa {
    private int nis;
    private String namaSiwa;
    private String jenisKelamin;
    private String konsentrasiKeahlian;

    public Siswa(int nis, String nama, String jk, String kk){
        this.nis=nis;
        this.namaSiwa=nama;
        this.jenisKelamin=jk;
        this.konsentrasiKeahlian=kk;
    }

    public int getNis() {
        return nis;
    }

    public String getNamaSiwa() {
        return namaSiwa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getKonsentrasiKeahlian() {
        return konsentrasiKeahlian;
    }
}
