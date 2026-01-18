package model;

public class Yoga extends Workout {

    public Yoga(int durasi) {
        super("Yoga", durasi);
    }

    @Override
    public int hitungKalori() {
        return durasi * 4;
    }
}
