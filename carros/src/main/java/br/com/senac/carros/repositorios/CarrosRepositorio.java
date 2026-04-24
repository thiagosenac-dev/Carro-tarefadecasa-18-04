package br.com.senac.carros.repositorios;

import br.com.senac.carros.entidades.Carros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrosRepositorio extends JpaRepository<Carros, Long> {
    List<Carros> findByModelo (String modelo);
}
