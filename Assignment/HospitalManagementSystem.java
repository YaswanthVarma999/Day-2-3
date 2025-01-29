package com.wt;

import java.util.*;

class Patient {
    private int id;
    private String name;
    private int age;
    private String ailment;

    public Patient(int id, String name, int age, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAilment() {
        return ailment;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Ailment: " + ailment;
    }
}

class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(int appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patient.getName() +
                ", Doctor: " + doctor.getName() + ", Date: " + date;
    }
}

public class HospitalManagementSystem {
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding sample doctors
        doctors.add(new Doctor(1, "Dr. Smith", "Cardiology"));
        doctors.add(new Doctor(2, "Dr. Johnson", "Dermatology"));
        doctors.add(new Doctor(3, "Dr. Brown", "Neurology"));

        boolean running = true;

        while (running) {
            System.out.println("\nHospital Management System Menu:");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addPatient(scanner);
                        break;
                    case 2:
                        viewPatients();
                        break;
                    case 3:
                        viewDoctors();
                        break;
                    case 4:
                        scheduleAppointment(scanner);
                        break;
                    case 5:
                        viewAppointments();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        scanner.close();
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Patient Ailment: ");
        String ailment = scanner.nextLine();

        patients.add(new Patient(id, name, age, ailment));
        System.out.println("Patient added successfully!");
    }

    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            System.out.println("\nPatients:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private static void viewDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("\nDoctors:");
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
    }

    private static void scheduleAppointment(Scanner scanner) {
        System.out.println("\nSelect a Patient:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Patient selectedPatient = patients.stream()
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);

        if (selectedPatient == null) {
            System.out.println("Invalid Patient ID!");
            return;
        }

        System.out.println("\nSelect a Doctor:");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Doctor selectedDoctor = doctors.stream()
                .filter(d -> d.getId() == doctorId)
                .findFirst()
                .orElse(null);

        if (selectedDoctor == null) {
            System.out.println("Invalid Doctor ID!");
            return;
        }

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(appointments.size() + 1, selectedPatient, selectedDoctor, date);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    private static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            System.out.println("\nAppointments:");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
}

