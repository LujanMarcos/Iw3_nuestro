package ar.edu.iua.rest;

import org.springframework.data.domain.Pageable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.iua.business.IProductoBusiness;
import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Producto;

@RestController
@RequestMapping(value = Constantes.URL_PRODUCTOS)
public class ProductosRestController extends BaseRestController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IProductoBusiness productoBusiness;

	@GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> list() {
		try {
			return new ResponseEntity<List<Producto>>(productoBusiness.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// curl -X POST "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"nombre":"Arroz","descripcion":"Arroz que no se pasa","precioLista":89.56,"enStock":true}' -v
	@PostMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(@RequestBody Producto producto) {
		try {
			productoBusiness.save(producto);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_PRODUCTOS + "/" + producto.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// curl -X PUT "http://localhost:8080/api/v1/productos" -H "Content-Type: application/json" -d '{"id":1,"nombre":"Arroz","descripcion":"Arroz que no se pasa","precioLista":76.32,"enStock":true}' -v
	@PutMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody Producto producto) {
		try {
			productoBusiness.save(producto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> load(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Producto>(productoBusiness.load(id),HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	// curl -X DELETE "http://localhost:8080/api/v1/productos/1"
	@DeleteMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			productoBusiness.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

    //curl -X GET  'http://localhost:8080/api/v1/productos/description?desc=arroz%20gallo%20de%20oro'
    @GetMapping(value = "/description")
    public ResponseEntity<Producto> loadByDescription(@RequestParam("desc") String desc) {
        try {
            return new ResponseEntity<Producto>(productoBusiness.findByDescripcionContains(desc), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }

    //curl -X GET  'http://localhost:8080/api/v1/productos/description?desc=arroz%20gallo%20de%20oro'
    @GetMapping(value = "/precio")
    public ResponseEntity<Producto> loadByPrecioMayor(@RequestParam("price") double precioMayor) {
        try {
            return new ResponseEntity<Producto>(productoBusiness.findByPrecioListaAfter(precioMayor), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/ingrediente")
    public ResponseEntity<List<Producto>> loadByIngredienteListDescripcion(@RequestParam("desc") String desc) {
        try {
            return new ResponseEntity<List<Producto>>(productoBusiness.findByIngredienteListDescripcion(desc), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<List<Producto>>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/productos_por_pagina")
    public Page<Producto> loadByPage(Pageable pageable) {
            return productoBusiness.findAllPage(pageable);

    }
}
