package model;

public class Cardio extends Workout {

    public Cardio(int durasi) {
        super("Cardio", durasi);
    }

    @Override
    public int hitungKalori() {
        return durasi * 8;
    }
}
