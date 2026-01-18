package controller;

import model.*;

public class AppController {
    private int totalKaloriMasuk = 0;
    private int totalKaloriKeluar = 0;

    public void tambahWorkout(Workout workout) {
        totalKaloriKeluar += workout.hitungKalori();
    }

    public void tambahMeal(Meal meal) {
        totalKaloriMasuk += meal.getKalori();
    }

    public int getSisaKalori(int target) {
        return target - totalKaloriMasuk + totalKaloriKeluar;
    }

    public int getTotalMealCalories() {
        return totalKaloriMasuk;
    }

    // 🔥 TAMBAHAN BARU
    public int getTotalKaloriKeluar() {
        return totalKaloriKeluar;
    }
}
