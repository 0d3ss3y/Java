package org.side.TUI;

public interface UI {
    static void heading() {
        System.out.println("============================================");
        System.out.println("           Welcome to BMI Calculator        ");
        System.out.println("============================================");
    }

    static void displayMetrics() {
        System.out.println("""
            Available Units:
            [1] Metric Units
                → Weight: kilograms (kg)
                → Height: meters (m)
            
            [2] US Customary Units
                → Weight: pounds (lbs)
                → Height: inches (in)
            """);
        System.out.print("Select an option (1-2):\n>");
    }

    static void weightInfo(String unit) {
        System.out.printf("""
                Weight Input
                ------------
                • Currently using: %s units
                • Please enter a positive number
                • For reference:
                    → Metric: typical adult range 30-200 kg
                    → US: typical adult range 66-440 lbs
                %n""", unit);
    }

    static void heightInfo(String unit) {
        System.out.printf("""
                Height Input
                ------------
                • Currently using: %s units
                • Please enter a positive number
                • For reference:
                    → Metric: typical adult range 1.0-2.5 m
                    → US: typical adult range 39-98 inches
                %n""", unit);
    }

    static void displayBMI(float bmi, String category) {
        System.out.println("\n============= BMI Results =============");
        System.out.printf("BMI Score: %.1f%n", bmi);  // Format to 1 decimal place
        System.out.println("Category: " + category);
        System.out.println("BMI Categories Reference:");
        System.out.println("• Underweight: < 18.5");
        System.out.println("• Healthy Weight: 18.5 - 24.9");
        System.out.println("• Overweight: 25.0 - 29.9");
        System.out.println("• Obese: ≥ 30.0");
        System.out.println("=====================================");
    }
}