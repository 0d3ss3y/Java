package org.side;

import java.util.Scanner;
import static org.side.TUI.UI.*;

public class BMI {
    private static final double UNDERWEIGHT_THRESHOLD = 18.5;
    private static final double HEALTHY_THRESHOLD = 24.9;
    private static final double OVERWEIGHT_THRESHOLD = 29.9;
    private static final double US_CONVERSION_FACTOR = 703;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            heading();
            String unit = getMetrics(scanner);
            float weight = getWeight(scanner, unit);
            float height = getHeight(scanner, unit);
            float bmi = calculateBMI(unit, weight, height);
            String category = determineStatus(bmi);
            displayBMI(bmi, category);
        }
    }

    private static String getMetrics(Scanner scanner) {
        String[] metrics = {"Metric", "US"};

        try {
            displayMetrics();
            int choice = scanner.nextInt();

            if (choice >= 1 && choice <= metrics.length) {
                return metrics[choice - 1];
            }
            System.out.println("Invalid choice. Selecting Default Option [Metric units]");
        } catch (Exception e) {
            System.out.println("Invalid input. Selecting Default Option [Metric units]");
        }
        return metrics[0];
    }

    private static float getWeight(Scanner scanner, String unit) {
        while (true) {
            weightInfo(unit);
            String unitLabel = unit.equals("Metric") ? "kg" : "pounds";
            System.out.printf("Enter Weight [%s] (%s)%n>", unit, unitLabel);

            try {
                float weight = scanner.nextFloat();
                if (weight <= 0) {
                    System.out.println("Weight must be positive");
                    continue;
                }
                if (weight > (unit.equals("Metric") ? 500 : 1100)) { // reasonable maximum weights
                    System.out.println("Weight seems unreasonable. Please verify.");
                    continue;
                }
                return weight;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear scanner buffer
            }
        }
    }

    private static float getHeight(Scanner scanner, String unit) {
        while (true) {
            heightInfo(unit);
            String unitLabel = unit.equals("Metric") ? "meters" : "inches";
            System.out.printf("Enter Height [%s] (%s)%n>", unit, unitLabel);

            try {
                float height = scanner.nextFloat();
                if (height <= 0) {
                    System.out.println("Height must be positive");
                    continue;
                }
                if (unit.equals("Metric")) {
                    if (height > 3.0) { // reasonable maximum height in meters
                        System.out.println("Height seems unreasonable. Please verify.");
                        continue;
                    }
                } else {
                    if (height > 108) { // reasonable maximum height in inches (9 feet)
                        System.out.println("Height seems unreasonable. Please verify.");
                        continue;
                    }
                }
                return height;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear scanner buffer
            }
        }
    }

    private static float calculateBMI(String unit, float weight, float height) {
        float bmi = weight / (float) Math.pow(height, 2);
        return unit.equals("Metric") ? bmi : (float) (bmi * US_CONVERSION_FACTOR);
    }

    private static String determineStatus(float bmi) {
        if (bmi <= UNDERWEIGHT_THRESHOLD) {
            return "Underweight";
        } else if (bmi <= HEALTHY_THRESHOLD) {
            return "Healthy Weight";
        } else if (bmi <= OVERWEIGHT_THRESHOLD) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}