package com.example.demo.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "public")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "The email is required")
    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @NotBlank(message = "The password is required")
    @Column(name = "password", nullable = false)
    private String password;

}
