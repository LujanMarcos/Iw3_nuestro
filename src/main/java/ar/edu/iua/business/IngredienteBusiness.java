package ar.edu.iua.business;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Ingrediente;
import ar.edu.iua.model.Producto;
import ar.edu.iua.model.persistence.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteBusiness implements IIngredienteBusiness {


    @Autowired
    private IngredienteRepository ingredienteDAO;

    @Override
    public Ingrediente load(Long id) throws BusinessException, NotFoundException {
        Optional<Ingrediente> op;
        try {
            op = ingredienteDAO.findById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
        if (!op.isPresent())
            throw new NotFoundException("No se encuentra el producto id=" + id);
        return op.get();
    }

    @Override
    public List<Ingrediente> list() throws BusinessException {
        try {
            return ingredienteDAO.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }


    @Override
    public List<Ingrediente>  findByProductoListPrecioLista(double precio) throws BusinessException {
        try {
            return ingredienteDAO.findByProductoListPrecioLista(precio);
        } catch (Exception e) {
            throw new BusinessException(e);
        }

    }

}
