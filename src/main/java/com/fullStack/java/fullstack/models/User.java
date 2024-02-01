package com.fullStack.java.fullstack.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;

    @Getter @Setter @Column(name = "name")
    private String name;

    @Getter @Setter @Column(name = "lastname")
    private String lastName;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "phone")
    private String phone;

    @Getter @Setter @Column(name = "password")
    private String password;


}
