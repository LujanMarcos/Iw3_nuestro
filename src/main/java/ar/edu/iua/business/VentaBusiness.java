package ar.edu.iua.business;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.Venta;
import ar.edu.iua.model.persistence.ProductoRepository;
import ar.edu.iua.model.persistence.VentaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaBusiness implements IVentaBusiness {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private VentaRepository ventaDAO;

    @Override
    public Venta load(Long id) throws BusinessException, NotFoundException {
        Optional<Venta> op;
        try {
            op = ventaDAO.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra la venta id = " + id);
        return op.get();
    }

    @Override
    public List<Venta> list() throws BusinessException {
        try {
            return ventaDAO.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public Venta save(Venta producto) throws BusinessException {
        try {
            return ventaDAO.save(producto);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public void delete(Long id) throws BusinessException, NotFoundException {
        try {
            ventaDAO.deleteById(id);
        } catch (EmptyResultDataAccessException e1) {
            throw new NotFoundException("No se encuentra la venta id = " + id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    public Page<Venta> findAllPage(Pageable pageable) {
        return ventaDAO.findAll(pageable);

    }



}
