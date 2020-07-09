package com.danilketov.testapp.entity;

import java.util.List;

public class Worker {

    private String firstName;

    private String lastName;

    private String birthday;

    private String avatarUrl;

    private List<Specialty> specialty;

    public Worker(String firstName, String lastName, String birthday, String avatarUrl, List<Specialty> specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.specialty = specialty;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Specialty> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<Specialty> specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (!firstName.equals(worker.firstName)) return false;
        if (!lastName.equals(worker.lastName)) return false;
        if (!birthday.equals(worker.birthday)) return false;
        if (!avatarUrl.equals(worker.avatarUrl)) return false;
        return specialty.equals(worker.specialty);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + avatarUrl.hashCode();
        result = 31 * result + specialty.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", specialty=" + specialty +
                '}';
    }
}
