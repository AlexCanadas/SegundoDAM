package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@NamedQueries({
    @NamedQuery(name = "Equipo.showTeams", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.deleteById", query = "DELETE FROM Equipo e WHERE e.id = :id"),
    @NamedQuery(name = "Equipo.modifyById", query = "UPDATE Equipo e SET e.nombre_equipo = :nuevo_nombre, e.ciudad = :nueva_ciudad WHERE e.id = :id")
})

@Entity
@Table(name="equipos")
public class Equipo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre_equipo;

    @Column
    private String ciudad;

    //Muchos equipos en una liga
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_liga")
    private Liga liga;

    //un jugador solo puede jugar en un equipo a la vez
    @OneToMany(mappedBy = "equipo")
    private List<Jugador> listaJugadores;

   //Un equipo tiene un entrenador
    @OneToOne(mappedBy = "equipo")
    private Entrenador entrenador;

    public Equipo(String nombre_equipo, String ciudad) {
        this.nombre_equipo = nombre_equipo;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre ='" + nombre_equipo + '\'' +
                ", ciudad='" + ciudad + '\'' +"\n"+
                '}';
    }
}
