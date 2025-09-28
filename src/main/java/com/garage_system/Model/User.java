package com.garage_system.Model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Users")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    private String name;
    
    @Column(unique = true)
    @Email(message="Email should be valid")
    private String email;
  
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList ;

    

   
}
