package back.clientes.model.entity;

import back.livros.model.entity.Livros;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Clientes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;

    @OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
    private List<Livros> livros = new ArrayList<>();

}
