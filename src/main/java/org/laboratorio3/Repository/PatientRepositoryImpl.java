package org.laboratorio3.Repository;

import org.laboratorio3.enums.BloodType;
import org.laboratorio3.enums.Gender;
import org.laboratorio3.models.Patient;
import org.laboratorio3.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository<Patient> {

    private Connection getConnection() {
        return ConnectionDB.getInstance();
    }

    @Override
    public void create(Patient patient) {
        String sql;
        boolean isUpdate = patient.getId() != null && patient.getId() > 0;

        if (isUpdate) {
            sql = "UPDATE Patient SET firstName = ?, lastName = ?, dateBirth = ?, address =?, gender = ?, bloodType = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO Patient (firstName, lastName, dateBirth, address, gender, bloodType) VALUES (?, ?, ?, ?, ?, ?)";
        }

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, patient.getFirstName());
            ps.setString(2, patient.getLastName());
            ps.setDate(3, new Date(patient.getDateBirth().getTime()));
            ps.setString(4, patient.getAddress());
            ps.setString(5, Gender.getGender(patient.getGender()));
            ps.setString(6, BloodType.getBloodType(patient.getBloodType()));

            if (isUpdate) {
                ps.setInt(7, patient.getId().intValue());
            }

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Patient patient) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = getConnection().prepareStatement("DELETE FROM Patient WHERE id = ?")) {
            ps.setInt(1, id.intValue());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
    }
}

    @Override
    public Patient read(Patient patient) {
        return null;
    }

    @Override
    public Patient readById(int id) {
        Patient patient = null;
        try (PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM Patient WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patient = createPatient(rs);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> readAll() {
        List<Patient> patients = new ArrayList<>();

        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Patient")){
            while (rs.next()){
                Patient patient = createPatient(rs);
                patients.add(patient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    private static Patient createPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setFirstName(rs.getString("firstName"));
        patient.setLastName(rs.getString("lastName"));
        patient.setDateBirth(rs.getDate("dateBirth"));
        patient.setAddress(rs.getString("address"));
        patient.setBloodType(BloodType.getBloodType(rs.getString("bloodType")));
        patient.setGender(Gender.getGender(rs.getString("gender")));
        return patient;
    }
}
