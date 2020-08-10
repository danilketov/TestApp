package com.danilketov.testapp.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.danilketov.testapp.utils.CustomTypeConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Worker {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @SerializedName("f_name")
    private String firstName;

    @SerializedName("l_name")
    private String lastName;

    private String birthday;

    @SerializedName("avatr_url")
    private String avatarUrl;

    @TypeConverters(CustomTypeConverter.class)
    private List<Specialty> specialty;

    public Worker(int id, @NonNull String firstName, String lastName, String birthday, String avatarUrl, List<Specialty> specialty) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
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

        if (id != worker.id) return false;
        if (!firstName.equals(worker.firstName)) return false;
        if (lastName != null ? !lastName.equals(worker.lastName) : worker.lastName != null)
            return false;
        if (birthday != null ? !birthday.equals(worker.birthday) : worker.birthday != null)
            return false;
        if (avatarUrl != null ? !avatarUrl.equals(worker.avatarUrl) : worker.avatarUrl != null)
            return false;
        return specialty != null ? specialty.equals(worker.specialty) : worker.specialty == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", specialty=" + specialty +
                '}';
    }
}
