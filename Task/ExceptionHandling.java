package com.wt;

public class ExceptionHandling {

    public static void checkEligibility(int age) throws Exception {
        if (age < 18) {
            throw new Exception("Not eligible, age must be 18 or older.");
        }
        System.out.println("Eligible!");
    }

    public static void main(String[] args) {
        int age = 16; 

        try {
            checkEligibility(age);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Eligibility check completed.");
        }
    }
}

