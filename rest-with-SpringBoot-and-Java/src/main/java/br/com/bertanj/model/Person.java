package br.com.bertanj.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    private static final long serialVersionUID= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 88)
    private String lastName;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "birth_date", nullable = false, length = 10)
    private Date birthDate;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false, length = 6)
    private String gender;


    public Person(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getPhoneNumber(), person.getPhoneNumber()) && Objects.equals(getBirthDate(), person.getBirthDate()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getGender(), person.getGender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber(), getBirthDate(), getAddress(), getGender());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
