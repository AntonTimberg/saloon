package com.example.saloon.entity;

import com.example.saloon.status.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Min(1)
    @Max(7)
    private Integer room;
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String surname;

    private String gender;

    @Column(name = "birthdate")
    private LocalDate birthDay;

    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String login;
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status = UserStatus.CLIENT;
}
