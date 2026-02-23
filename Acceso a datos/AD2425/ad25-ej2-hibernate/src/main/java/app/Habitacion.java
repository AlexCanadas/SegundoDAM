package app;

import jakarta.persistence.*;

@Entity
@Table(name = "Habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;
    private Integer planta;
    private Integer capacidad;

    public Habitacion() {}

    public Habitacion(Integer numero, Integer planta, Integer capacidad) {
        this.numero = numero;
        this.planta = planta;
        this.capacidad = capacidad;
    }

    public Long getId() { return id; }
    public Integer getNumero() { return numero; }
    public Integer getPlanta() { return planta; }
    public Integer getCapacidad() { return capacidad; }

    public void setNumero(Integer numero) { this.numero = numero; }
    public void setPlanta(Integer planta) { this.planta = planta; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
}
