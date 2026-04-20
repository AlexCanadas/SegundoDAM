package com.example.peliculasapi.controller;

import com.example.peliculasapi.model.Pelicula;
import com.example.peliculasapi.repository.PeliculaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    private final PeliculaRepository repository;

    public PeliculaController(PeliculaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pelicula> obtenerTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return repository.save(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        pelicula.setId(id);
        return repository.save(pelicula);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}