package model;

public enum FitnessGoal {
    BULKING(300),
    CUTTING(-300),
    MAINTENANCE(0);

    private int adjustment;

    FitnessGoal(int adjustment) {
        this.adjustment = adjustment;
    }

    public int getAdjustment() {
        return adjustment;
    }
}
