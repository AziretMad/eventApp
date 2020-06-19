package com.company.eventApp.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "login")
    String login;

    @Column(name = "password")
    String password;

    @Email(message = "please provide valid email")
    @Column(name = "email")
    String email;

    @Column(name = "telephone")
    String telephone;

    @Column(name = "is_active")
    Integer isActive;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();
}
