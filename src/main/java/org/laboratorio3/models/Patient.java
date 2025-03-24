package org.laboratorio3.models;

import org.laboratorio3.enums.BloodType;
import org.laboratorio3.enums.Gender;

import java.util.Date;

public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateBirth;
    private String address;
    private Gender gender;
    private BloodType bloodType;

    public Patient(String firstName, String lastName, Date dateBirth, String address, Gender gender, BloodType bloodType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.address = address;
        this.gender = gender;
        this.bloodType = bloodType;
    }

    public Patient() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + dateBirth + " " + address + " " + bloodType + " " + gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
