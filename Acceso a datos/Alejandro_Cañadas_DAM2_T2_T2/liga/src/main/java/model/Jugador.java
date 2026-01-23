package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="jugadores")
public class Jugador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private Double valor_mercado;

    @Column
    private int goles;

    @Column
    private String nacionalidad;

    //Muchos jugadores en una posición
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_posicion")
    private Posicion posicion;

    //Muchos jugadores en un equipo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_equipo")
    private Equipo equipo;


    public Jugador(String nombre, Double valor_mercado, int goles, String nacionalidad) {
        this.nombre = nombre;
        this.valor_mercado = valor_mercado;
        this.goles = goles;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", valor_mercado=" + valor_mercado +
                ", goles=" + goles +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", posicion=" + posicion.getPosicion().toString() +
                ", equipo=" + equipo +
                '}';
    }
}
