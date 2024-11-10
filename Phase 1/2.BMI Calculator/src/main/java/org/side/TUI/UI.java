package org.side.TUI;

public interface UI {
    static void heading() {
        System.out.println("Welcome to the BMI Cal");
    }

    static void displayMetrics(){
        System.out.println("""
                Available Metrics:
                [1] Metric Units
                    -> weight = kg
                    -> height = m
                
                [2] US Customary Units:
                    -> weight = pounds
                    -> height = inches
                """);

        System.out.print("Select an option:\n>");
    }

    static void weightInfo(String Metric){
        System.out.println("""
                [1]. Selected metric units [" + Metric + "]
                [2]. Enter your weight
                [3]. Continue
                """);
    }

    static void heightInfo(String Metric){
        System.out.println("""
                [1]. Selected metric units [" + Metric + "]
                [2]. Enter your weight
                [3]. Continue
                """);
    }

    static void displayBMI(float bmi, String category){
        System.out.println("=============");
        System.out.println("BMI score: " + bmi);
        System.out.println("Categories: "+ category);
        System.out.println("=============");
    }
}
