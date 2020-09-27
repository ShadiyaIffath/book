package com.bookstore.book.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable=false)
    private String firstName;

    @Column(length = 50, nullable=false)
    private String lastName;

    @Column(length = 50, nullable=false, unique = true)
    private String email;

    @Column(length = 10, nullable=false)
    private Integer phone;

    @Column(length = 250, nullable=false)
    private String password;

    @Column(length = 12, nullable = false)
    private String type;

    @Column(nullable=false)
    private Boolean active;

    @OneToMany( mappedBy = "account",cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany( mappedBy = "account",cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany( mappedBy = "account",cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Account(String firstName,String lastName,String email, Integer phone,String password, String type, Boolean active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.type = type;
        this.active = active;
    }
}
