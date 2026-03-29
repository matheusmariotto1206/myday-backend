package br.com.fiap.myday.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "md_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
}
