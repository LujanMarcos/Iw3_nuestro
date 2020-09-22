package ar.edu.iua.rest;

import ar.edu.iua.business.IIngredienteBusiness;
import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Ingrediente;
import ar.edu.iua.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Constantes.URL_INGREDIENTES)
public class IngredienteRestController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIngredienteBusiness ingredienteBusiness;

    @GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ingrediente>> list() {
        try {
            return new ResponseEntity<List<Ingrediente>>(ingredienteBusiness.list(), HttpStatus.OK);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<List<Ingrediente>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/precio_producto")
    public ResponseEntity<List<Ingrediente>> loadByProductoPrecio(@RequestParam("precio") double precio) {
        try {
            return new ResponseEntity<List<Ingrediente>>(ingredienteBusiness.findByProductoListPrecioLista(precio), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<List<Ingrediente>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

