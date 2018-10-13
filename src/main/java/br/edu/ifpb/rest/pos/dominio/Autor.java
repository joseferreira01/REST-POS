/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jose
 */
@Entity
@XmlRootElement
public class Autor implements Comparable<Autor>, Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private String abreacao;

    public Autor() {
    }

    public Autor(String nome, String email, String abreacao) {

        this.nome = nome;
        this.email = email;
        this.abreacao = abreacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbreacao() {
        return abreacao;
    }

    public void setAbreacao(String abreacao) {
        this.abreacao = abreacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", abreacao=" + abreacao + '}';
    }

    @Override
    public int compareTo(Autor o) {
        return this.getNome().compareToIgnoreCase(o.getNome());
    }

}
