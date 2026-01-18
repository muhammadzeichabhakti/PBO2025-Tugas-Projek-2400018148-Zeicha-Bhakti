package model;

public class User {
    private String nama;
    private double beratBadan;
    private int targetKalori;

    public User(String nama, double beratBadan, int targetKalori) {
        this.nama = nama;
        this.beratBadan = beratBadan;
        this.targetKalori = targetKalori;
    }

    public String getNama() {
        return nama;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public int getTargetKalori() {
        return targetKalori;
    }
}
