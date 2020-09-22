package ar.edu.iua.model.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.Producto;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByDescripcion(String descripcionProducto);

    Optional<Producto> findByDescripcionContains(String descripcionProducto);

    Optional<Producto> findByPrecioListaAfter(double precio);

    List<Producto> findByIngredienteListDescripcionIngrediente(String descripcionIngrediente);

    Page<Producto> findAll(Pageable pageable);



}
