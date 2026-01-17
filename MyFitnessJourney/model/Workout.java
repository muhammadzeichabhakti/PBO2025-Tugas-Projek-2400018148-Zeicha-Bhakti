package model;

public abstract class Workout {
    protected String nama;
    protected int durasi; // menit

    public Workout(String nama, int durasi) {
        this.nama = nama;
        this.durasi = durasi;
    }

    public abstract int hitungKalori();

    public String getNama() {
        return nama;
    }
}
