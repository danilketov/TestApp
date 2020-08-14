package com.danilketov.testapp.utils;

public class Parameters {

    private String lastName;
    private String firstName;
    private String age;
    private String birthday;
    private String avatar;
    private String specialtyJSON;

    public Parameters(String lastName, String firstName, String age, String birthday, String avatar, String specialtyJSON) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.birthday = birthday;
        this.avatar = avatar;
        this.specialtyJSON = specialtyJSON;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getSpecialtyJSON() {
        return specialtyJSON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameters that = (Parameters) o;

        if (!lastName.equals(that.lastName)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!age.equals(that.age)) return false;
        if (!birthday.equals(that.birthday)) return false;
        if (!avatar.equals(that.avatar)) return false;
        return specialtyJSON.equals(that.specialtyJSON);
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + avatar.hashCode();
        result = 31 * result + specialtyJSON.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                ", birthday='" + birthday + '\'' +
                ", avatar='" + avatar + '\'' +
                ", specialtyJSON='" + specialtyJSON + '\'' +
                '}';
    }
}
