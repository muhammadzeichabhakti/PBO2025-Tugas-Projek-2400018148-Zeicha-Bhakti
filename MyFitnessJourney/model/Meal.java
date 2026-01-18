    package model;

    public class Meal {
        private String namaMakanan;
        private int kalori;

        public Meal(String namaMakanan, int kalori) {
            this.namaMakanan = namaMakanan;
            this.kalori = kalori;
        }

        public int getKalori() {
            return kalori;
        }
    }
