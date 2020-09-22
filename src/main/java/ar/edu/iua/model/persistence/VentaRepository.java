package ar.edu.iua.model.persistence;

import ar.edu.iua.business.IVentaBusiness;
import ar.edu.iua.model.Ingrediente;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Ingrediente> findByProductoListPrecioLista(double precioLista);


}
