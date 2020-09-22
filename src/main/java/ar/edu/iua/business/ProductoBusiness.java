package ar.edu.iua.business;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.ProductoRepository;

@Service
public class ProductoBusiness implements IProductoBusiness {

    private Logger log = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private ProductoRepository productoDAO;

	@Override
	public Producto load(Long id) throws BusinessException, NotFoundException {
		Optional<Producto> op;
		try {
			op = productoDAO.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		if (!op.isPresent())
			throw new NotFoundException("No se encuentra el producto id=" + id);
		return op.get();
	}

	@Override
	public List<Producto> list() throws BusinessException {
		try {
			return productoDAO.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Producto save(Producto producto) throws BusinessException {
		try {
			return productoDAO.save(producto);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Long id) throws BusinessException,NotFoundException {
		try {
			productoDAO.deleteById(id);
		} catch (EmptyResultDataAccessException e1) {
			throw new NotFoundException("No se encuentra el producto id=" + id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}


    @Override
    public Producto findByDescripcionContains(String descripcionProducto) throws BusinessException, NotFoundException {
        Optional<Producto> op = null;
        try {
            log.info("Getting by description");
            op = productoDAO.findByDescripcionContains(descripcionProducto);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra el producto con descripcion=" + descripcionProducto);
        return op.get();

    }

    @Override
    public Producto findByPrecioListaAfter(double precioMayor) throws BusinessException, NotFoundException {
        Optional<Producto> op = null;
        try {
            log.info("Getting by precio");
            op = productoDAO.findByPrecioListaAfter(precioMayor);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra el producto con precio mayor a " + precioMayor);
        return op.get();

    }

    @Override
    public List<Producto>  findByIngredienteListDescripcion(String descripcion) throws BusinessException, NotFoundException {
        try {
            return productoDAO.findByIngredienteListDescripcionIngrediente(descripcion);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e);
        }

    }

    public Page<Producto> findAllPage(Pageable pageable) {
            return productoDAO.findAll(pageable);

    }

}
