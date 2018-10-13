/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose
 */
@Entity
@XmlRootElement
public class Livro implements Comparable<Livro>, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String editora;
    private String descricao;
    private String titulo;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Autor> autores = new ArrayList<>();

    public Livro() {
         this.id = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public Livro(String id, String editora, String descricao, String titulo) {
        
        this.id = id;
        this.editora = editora;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public Livro(String editora, String descricao, String titulo) {
        this();
        this.editora = editora;
        this.descricao = descricao;
        this.titulo = titulo;
    }

    public void novoAutor(Autor autor) {
        this.autores.add(autor);
    }

    public void removeAutor(Autor autor) {
        this.autores.remove(autor);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Livro other = (Livro) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return Collections.unmodifiableList(autores);
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public int compareTo(Livro o) {
        return this.titulo.compareTo(o.titulo);
    }

}
