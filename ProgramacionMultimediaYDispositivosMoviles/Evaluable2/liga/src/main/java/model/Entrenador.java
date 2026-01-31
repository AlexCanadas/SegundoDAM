package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@NamedQuery(name = "Entrenador.deleteCoachById", query = "DELETE FROM Entrenador e WHERE e.id = :id")

@Entity
@Table(name="entrenadores")
public class Entrenador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private Double calificacion;

    @Column
    private int titulos;

    //Un equipo tiene un entrenador
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_equipo")
    private Equipo equipo;

    public Entrenador(String nombre, Double calificacion, int titulos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.titulos = titulos;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre=" + nombre +
                ", calificacion=" + calificacion +
                ", titulos=" + titulos +
                ", equipo=" + equipo +
                '}';
    }
}
