package com.peruvianfarma.appweb.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
<<<<<<< HEAD
=======

import java.io.Serializable;

>>>>>>> origin/master
import javax.persistence.Column;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
<<<<<<< HEAD
public class Usuario {
=======
public class Usuario implements Serializable{
>>>>>>> origin/master
    @Id
    @Column(name = "user_id")
    private String userID;
    private String password;
    private String tipoUsuario;

}