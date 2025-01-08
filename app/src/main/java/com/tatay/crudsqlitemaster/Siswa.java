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

    public void setNis(int nis) {
        this.nis = nis;
    }

    public void setNamaSiwa(String namaSiwa) {
        this.namaSiwa = namaSiwa;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setKonsentrasiKeahlian(String konsentrasiKeahlian) {
        this.konsentrasiKeahlian = konsentrasiKeahlian;
    }
}
