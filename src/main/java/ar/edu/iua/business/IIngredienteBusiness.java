package ar.edu.iua.business;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Ingrediente;
import ar.edu.iua.model.Producto;

import java.util.List;

public interface IIngredienteBusiness {

    public Ingrediente load(Long id) throws BusinessException, NotFoundException;

    public List<Ingrediente> list() throws BusinessException;

    public List<Ingrediente> findByProductoListPrecioLista(double precioLista) throws BusinessException;


}
