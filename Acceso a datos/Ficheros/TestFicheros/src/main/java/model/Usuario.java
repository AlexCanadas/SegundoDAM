package model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int id;
    private String nombre, apellido, correo, dni;
    private int telefono;




}
