package ar.edu.iua.model.persistence;

import ar.edu.iua.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository  extends JpaRepository<Ingrediente, Long> {

    List<Ingrediente> findByProductoListPrecioLista(double precioLista);

}
