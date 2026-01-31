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
@AllArgsConstructor
@NoArgsConstructor


@NamedQueries({
        @NamedQuery(name="Liga.editById", query="UPDATE Liga l SET l.nombre_liga = :nuevonombre, l.fecha_inicio = :nuevoinicio, l.fecha_fin = :nuevofin WHERE l.id = :id"),
        @NamedQuery(name="Liga.deleteById", query="DELETE FROM Liga l WHERE l.id = :id"),
        @NamedQuery(name="Liga.findAll", query="FROM Liga l WHERE l.id =:id")
})

@Entity
@Table(name="ligas")
public class Liga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre_liga;

    @Column
    private String fecha_inicio;

    @Column
    private String fecha_fin;

    //Una liga está formada por muchos equipos
    @OneToMany(mappedBy = "liga", cascade = CascadeType.REMOVE)
    private List<Equipo> listEquipo;

    public Liga(String nombre_liga, String fecha_inicio, String fecha_fin) {
        this.nombre_liga = nombre_liga;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Liga(String nombre_liga, String fecha_inicio, String fecha_fin, List<Equipo> listEquipo) {
        this.nombre_liga = nombre_liga;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.listEquipo = listEquipo;
    }

    @Override
    public String toString() {
        return "Liga{" +
                nombre_liga + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                '}';
    }
}
