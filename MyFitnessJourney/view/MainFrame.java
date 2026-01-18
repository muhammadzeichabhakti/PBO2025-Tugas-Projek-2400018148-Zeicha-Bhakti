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
    private JComboBox<FitnessGoal> cbGoal;

    // output
    private JLabel lblAdjustedTarget;
    private JLabel lblMealResult;
    private JLabel lblKaloriKeluar;
    private JLabel lblKurangKalori;
    private JLabel lblSisaKalori;
    private JLabel lblStatus;
    private JTextArea txtSummary;

    public MainFrame() {
        setTitle("MyFitJourney");
        setSize(600, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        /* ===== PANEL INPUT ===== */
        JPanel panelInput = new JPanel(new GridLayout(6, 2, 10, 10));

        txtDurasi = new JTextField();
        txtTargetKalori = new JTextField();
        txtMealCalories = new JTextField();

        cbWorkout = new JComboBox<>(new String[]{
                "Cardio", "Weight Training", "Yoga"
        });

        cbGoal = new JComboBox<>(FitnessGoal.values());

        panelInput.add(new JLabel("Durasi Workout (menit):"));
        panelInput.add(txtDurasi);

        panelInput.add(new JLabel("Target Kalori Harian:"));
        panelInput.add(txtTargetKalori);

        panelInput.add(new JLabel("Kalori Makanan:"));
        panelInput.add(txtMealCalories);

        panelInput.add(new JLabel("Jenis Workout:"));
        panelInput.add(cbWorkout);

        panelInput.add(new JLabel("Tujuan Fitness:"));
        panelInput.add(cbGoal);

        add(panelInput, BorderLayout.NORTH);

        /* ===== TOMBOL ===== */
        JButton btnHitung = new JButton("Hitung");
        add(btnHitung, BorderLayout.CENTER);

        /* ===== PANEL OUTPUT ===== */
        JPanel panelOutput = new JPanel(new GridLayout(7, 1, 5, 5));

        lblAdjustedTarget = new JLabel("Target Disesuaikan: -", SwingConstants.CENTER);
        lblMealResult = new JLabel("Kalori Masuk: 0", SwingConstants.CENTER);
        lblKaloriKeluar = new JLabel("Kalori Keluar: 0", SwingConstants.CENTER);
        lblKurangKalori = new JLabel("Kalori Kurang: -", SwingConstants.CENTER);
        lblSisaKalori = new JLabel("Ruang Kalori Tersisa: -", SwingConstants.CENTER);
        lblStatus = new JLabel("Status: -", SwingConstants.CENTER);

        lblAdjustedTarget.setFont(new Font("Arial", Font.BOLD, 14));
        lblSisaKalori.setFont(new Font("Arial", Font.BOLD, 16));

        txtSummary = new JTextArea(3, 20);
        txtSummary.setEditable(false);
        txtSummary.setLineWrap(true);
        txtSummary.setWrapStyleWord(true);
        txtSummary.setBorder(
                BorderFactory.createTitledBorder("Ringkasan Otomatis")
        );

        panelOutput.add(lblAdjustedTarget);
        panelOutput.add(lblMealResult);
        panelOutput.add(lblKaloriKeluar);
        panelOutput.add(lblKurangKalori);
        panelOutput.add(lblSisaKalori);
        panelOutput.add(lblStatus);
        panelOutput.add(txtSummary);

        add(panelOutput, BorderLayout.SOUTH);

        /* ===== LOGIKA ===== */
        btnHitung.addActionListener(e -> {
            try {
                int durasi = Integer.parseInt(txtDurasi.getText());
                int target = Integer.parseInt(txtTargetKalori.getText());
                int mealCal = Integer.parseInt(txtMealCalories.getText());

                FitnessGoal goal = (FitnessGoal) cbGoal.getSelectedItem();
                int adjustedTarget = target + goal.getAdjustment();

                Workout workout;
                String jenis = cbWorkout.getSelectedItem().toString();

                if (jenis.equals("Cardio")) {
                    workout = new Cardio(durasi);
                } else if (jenis.equals("Weight Training")) {
                    workout = new WeightTraining(durasi);
                } else {
                    workout = new Yoga(durasi);
                }

                controller.tambahWorkout(workout);
                controller.tambahMeal(new Meal("Makanan", mealCal));

                int kaloriMasuk = controller.getTotalMealCalories();
                int kaloriKeluar = controller.getTotalKaloriKeluar();
                int sisa = controller.getSisaKalori(adjustedTarget);
                int kurang = adjustedTarget - kaloriMasuk;

                lblAdjustedTarget.setText(
                        "Target Disesuaikan: " + adjustedTarget + " kcal"
                );
                lblMealResult.setText("Kalori Masuk: " + kaloriMasuk + " kcal");
                lblKaloriKeluar.setText("Kalori Keluar: " + kaloriKeluar + " kcal");
                lblKurangKalori.setText("Kalori Kurang: " + kurang + " kcal");
                lblSisaKalori.setText("Ruang Kalori Tersisa: " + sisa + " kcal");

                if (kurang > 0) {
                    lblStatus.setText("Status: Kalori Masih Kurang 🔴");
                } else {
                    lblStatus.setText("Status: Target Kalori Tercapai 🟢");
                }

                int kaloriWorkout = workout.hitungKalori();
                String ringkasan =
                        "Workout " + workout.getNama() +
                                " selama " + durasi + " menit membakar " +
                                kaloriWorkout + " kcal. ";

                ringkasan += (kurang > 0)
                        ? "Target kalori belum tercapai."
                        : "Target kalori sudah tercapai.";

                txtSummary.setText(ringkasan);

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
