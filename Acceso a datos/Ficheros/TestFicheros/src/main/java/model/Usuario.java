package model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario implements Serializable {
    private static Long serialVersionUID = 1L; // Etiqueta del objeto
    private int id;
    private String nombre, apellido, correo, dni;
    private int telefono;




}
