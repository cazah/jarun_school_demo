package com.jarun.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @Column(name = "joining_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date joiningDate;

    @Column(name = "class")
    private String className;
}

