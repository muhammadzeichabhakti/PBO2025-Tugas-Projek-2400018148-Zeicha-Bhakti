package view;

import controller.AppController;
import model.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private AppController controller = new AppController();

    // input
    private JTextField txtDurasi;
    private JTextField txtTargetKalori;
    private JTextField txtMealCalories;
    private JComboBox<String> cbWorkout;

    // output
    private JLabel lblResult;
    private JLabel lblMealResult;
    private JLabel lblKurangKalori;

    public MainFrame() {
        setTitle("MyFitJourney");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        /* ===== PANEL INPUT ===== */
        JPanel panelInput = new JPanel(new GridLayout(6, 2, 10, 10));

        txtDurasi = new JTextField();
        txtTargetKalori = new JTextField();
        txtMealCalories = new JTextField();
        cbWorkout = new JComboBox<>(new String[]{"Cardio", "Weight Training"});

        panelInput.add(new JLabel("Durasi Workout (menit):"));
        panelInput.add(txtDurasi);

        panelInput.add(new JLabel("Target Kalori Harian:"));
        panelInput.add(txtTargetKalori);

        panelInput.add(new JLabel("Kalori Makanan:"));
        panelInput.add(txtMealCalories);

        panelInput.add(new JLabel("Jenis Workout:"));
        panelInput.add(cbWorkout);

        JTextField txtBeratBadan = new JTextField();

        panelInput.add(new JLabel("Berat Badan (kg):"));
        panelInput.add(txtBeratBadan);


        add(panelInput, BorderLayout.NORTH);

        /* ===== TOMBOL ===== */
        JButton btnHitung = new JButton("Hitung");
        add(btnHitung, BorderLayout.CENTER);

        /* ===== PANEL OUTPUT ===== */
        JPanel panelOutput = new JPanel(new GridLayout(3, 1));

        lblResult = new JLabel("Sisa Kalori Hari Ini: -", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        lblMealResult = new JLabel("Kalori Masuk: 0", SwingConstants.CENTER);
        lblKurangKalori = new JLabel("Kalori Kurang: -", SwingConstants.CENTER);

        panelOutput.add(lblResult);
        panelOutput.add(lblMealResult);
        panelOutput.add(lblKurangKalori);

        add(panelOutput, BorderLayout.SOUTH);

        /* ===== LOGIKA ===== */
        btnHitung.addActionListener(e -> {
            try {
                int durasi = Integer.parseInt(txtDurasi.getText());
                int target = Integer.parseInt(txtTargetKalori.getText());
                int mealCal = Integer.parseInt(txtMealCalories.getText());

                Workout workout;
                if (cbWorkout.getSelectedItem().equals("Cardio")) {
                    workout = new Cardio(durasi);
                } else {
                    workout = new WeightTraining(durasi);
                }

                controller.tambahWorkout(workout);
                controller.tambahMeal(new Meal("Makanan", mealCal));

                int kaloriMasuk = controller.getTotalMealCalories();
                int kaloriKurang = target - kaloriMasuk;
                int sisa = controller.getSisaKalori(target);

                lblMealResult.setText("Kalori Masuk: " + kaloriMasuk);
                lblKurangKalori.setText("Kalori Kurang: " + kaloriKurang);
                lblResult.setText("Sisa Kalori Hari Ini: " + sisa);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Input harus berupa angka!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        setVisible(true);
    }
}
