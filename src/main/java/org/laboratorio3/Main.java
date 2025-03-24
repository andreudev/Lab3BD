package org.laboratorio3;

import org.laboratorio3.Repository.PatientRepository;
import org.laboratorio3.Repository.PatientRepositoryImpl;
import org.laboratorio3.enums.BloodType;
import org.laboratorio3.enums.Gender;
import org.laboratorio3.models.Patient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        PatientRepository<Patient> patientRepository = new PatientRepositoryImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Create Patient");
            System.out.println("2. Read Patient by ID");
            System.out.println("3. Read All Patients");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    createPatient(scanner, patientRepository);
                    break;
                case 2:
                    readPatientById(scanner, patientRepository);
                    break;
                case 3:
                    readAllPatients(patientRepository);
                    break;
                case 4:
                    updatePatient(scanner, patientRepository);
                    break;
                case 5:
                    deletePatient(scanner, patientRepository);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createPatient(Scanner scanner, PatientRepository<Patient> patientRepository) {
        try {
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter date of birth (yyyy-MM-dd): ");
            Date dateBirth = dateFormat.parse(scanner.nextLine());
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter gender (Male/Female/Other): ");
            Gender gender = Gender.getGender(scanner.nextLine());
            System.out.print("Enter blood type (A+/A-/B+/B-/AB+/AB-/O+/O-): ");
            BloodType bloodType = BloodType.getBloodType(scanner.nextLine());

            Patient patient = new Patient(firstName, lastName, dateBirth, address, gender, bloodType);
            patientRepository.create(patient);
            System.out.println("Patient created successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void readPatientById(Scanner scanner, PatientRepository<Patient> patientRepository) {
        System.out.print("Enter patient ID: ");
        int id = scanner.nextInt();
        Patient patient = patientRepository.readById(id);
        if (patient != null) {
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void readAllPatients(PatientRepository<Patient> patientRepository) {
        List<Patient> patients = patientRepository.readAll();
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void updatePatient(Scanner scanner, PatientRepository<Patient> patientRepository) {
        try {
            System.out.print("Enter patient ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Patient patient = patientRepository.readById(id);
            if (patient != null) {
                System.out.print("Enter new first name: ");
                patient.setFirstName(scanner.nextLine());
                System.out.print("Enter new last name: ");
                patient.setLastName(scanner.nextLine());
                System.out.print("Enter new date of birth (yyyy-MM-dd): ");
                patient.setDateBirth(dateFormat.parse(scanner.nextLine()));
                System.out.print("Enter new address: ");
                patient.setAddress(scanner.nextLine());
                System.out.print("Enter new gender (Male/Female/Other): ");
                patient.setGender(Gender.getGender(scanner.nextLine()));
                System.out.print("Enter new blood type (A+/A-/B+/B-/AB+/AB-/O+/O-): ");
                patient.setBloodType(BloodType.getBloodType(scanner.nextLine()));

                patientRepository.create(patient);
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void deletePatient(Scanner scanner, PatientRepository<Patient> patientRepository) {
        System.out.print("Enter patient ID to delete: ");
        Long id = scanner.nextLong();
        patientRepository.delete(id);
        System.out.println("Patient deleted successfully.");
    }
}