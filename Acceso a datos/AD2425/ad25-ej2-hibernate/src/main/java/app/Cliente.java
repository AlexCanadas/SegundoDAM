package app;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(name = "telefono")
    private Integer telefono;

    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    public Cliente() {}

    public Cliente(String nombre, String apellido, Integer telefono, String ciudad, Habitacion habitacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.habitacion = habitacion;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Integer getTelefono() { return telefono; }
    public String getCiudad() { return ciudad; }
    public Habitacion getHabitacion() { return habitacion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(Integer telefono) { this.telefono = telefono; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setHabitacion(Habitacion habitacion) { this.habitacion = habitacion; }
}
