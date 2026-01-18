package model;

public class WeightTraining extends Workout {

    public WeightTraining(int durasi) {
        super("Weight Training", durasi);
    }

    @Override
    public int hitungKalori() {
        return durasi * 6;
    }
}
