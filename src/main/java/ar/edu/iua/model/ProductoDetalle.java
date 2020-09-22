package ar.edu.iua.model;


import javax.persistence.*;

@Entity
@Table(name="producto_detalle")
public class ProductoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150)
    private String fabrica;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFabrica() {
        return fabrica;
    }
    public void setFabrica(String fabrica) {
        this.fabrica = fabrica;
    }

}

