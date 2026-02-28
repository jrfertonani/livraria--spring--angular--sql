package back.livros.model.entity;

import back.clientes.model.entity.Clientes;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Livros implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String autor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

}
