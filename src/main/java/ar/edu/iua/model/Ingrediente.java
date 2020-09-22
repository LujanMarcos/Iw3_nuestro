package ar.edu.iua.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ingredientes")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")


public class Ingrediente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "descripcion", length = 100)
    private String descripcionIngrediente;

    @ManyToMany(targetEntity = Producto.class, mappedBy = "ingredienteList")
    @JsonBackReference
    private List<Producto> productoList;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcionIngrediente() {
        return descripcionIngrediente;
    }
    public void setDescripcionIngrediente(String descripcionIngrediente) {
        this.descripcionIngrediente = descripcionIngrediente;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

}
