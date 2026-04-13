package model;

import jakarta.persistence.*;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "numero")
    private int numero;

    @Column(name = "planta")
    private int planta;

    @Column(name = "capacidad")
    private int capacidad;

    public Habitacion() {
    }

    public Habitacion(int numero, int planta, int capacidad) {
        this.numero = numero;
        this.planta = planta;
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPlanta() {
        return planta;
    }

    public void setPlanta(int planta) {
        this.planta = planta;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}