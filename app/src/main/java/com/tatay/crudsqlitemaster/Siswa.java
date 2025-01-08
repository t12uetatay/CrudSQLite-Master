package com.tatay.crudsqlitemaster;

public class Siswa {
    private int nis;
    private String namaSiswa;
    private String jenisKelamin;
    private String konsentrasiKeahlian;

    public Siswa(int nis, String nama, String jk, String kk){
        this.nis=nis;
        this.namaSiswa=nama;
        this.jenisKelamin=jk;
        this.konsentrasiKeahlian=kk;
    }

    public int getNis() {
        return nis;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getKonsentrasiKeahlian() {
        return konsentrasiKeahlian;
    }
}
