package ar.edu.iua.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "productos")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")

public class Producto implements Serializable {

	private static final long serialVersionUID = 451621105748580924L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String nombre;
	@Column(length = 250)
	private String descripcion;
	private double precioLista;
	@Column(columnDefinition = "TINYINT DEFAULT 0")
	private boolean enStock;



    @OneToOne(cascade =  CascadeType.ALL)
    private ProductoDetalle productoDetalle;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;



    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "producto_ingrediente_detalle",
            joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id", referencedColumnName = "id"))
    private List<Ingrediente> ingredienteList;

	@ManyToMany(targetEntity = Venta.class, mappedBy = "productoList")
	@JsonBackReference
	private List<Venta> ventaList;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioLista() {
		return precioLista;
	}

	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	}

	public boolean isEnStock() {
		return enStock;
	}

	public void setEnStock(boolean enStock) {
		this.enStock = enStock;
	}

    public ProductoDetalle getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(ProductoDetalle productoDetalle) {
        this.productoDetalle = productoDetalle;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    public void setIngredienteList(List<Ingrediente> ingredienteList) {
        this.ingredienteList = ingredienteList;
    }

}
