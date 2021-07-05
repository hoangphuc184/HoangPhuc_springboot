package com.example.springboot.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(
            strategy= GenerationType.IDENTITY
    )
    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name = "fullname")
    private String full_name;

    @Column(name = "email")
    private String email;

    @Column(name = "emp_role")
    private String role;

    public Employee() {
    }

    public Employee(Long id, String full_name, String email, String role) {
        this.employee_id = id;
        this.full_name = full_name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return employee_id;
    }

    public void setId(Long id) {
        this.employee_id = id;
    }

    public String getName() {
        return full_name;
    }

    public void setName(String name) {
        this.full_name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
