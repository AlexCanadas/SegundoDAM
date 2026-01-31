package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posiciones")
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String posicion;

    //Una posición (defensa por ej) puede ser ocupada por varios jugadores
    @OneToMany(mappedBy = "posicion")
    private List<Jugador> jugadores;

    public Posicion(String posicion) {

        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Posicion{" +
                "id=" + id +
                ", posicion='" + posicion + '\'' +
                '}';
    }
}
