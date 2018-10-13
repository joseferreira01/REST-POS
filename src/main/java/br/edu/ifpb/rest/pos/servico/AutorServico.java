/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.servico;

import br.edu.ifpb.rest.pos.dominio.Autor;
import br.edu.ifpb.rest.pos.infra.Repositorio;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jose
 */
@Stateless
public class AutorServico {

    @EJB
    private Repositorio<Autor, Long> repositorio;

    public Autor salvar(Autor autor) {
        return this.repositorio.salvar(autor);
    }

    public List<Autor> todos() {
        return this.repositorio.list(Autor.class);
    }

    public Optional<Autor> LivroComId(Long id) {
        Autor autor = repositorio.find(Autor.class, id);

        return Optional.of(autor);

    }

    public Optional<Autor> removerAutorCom(Long id) {
        Optional<Autor> autor = LivroComId(id);

        if (autor.isPresent()) {
            this.repositorio.remove(autor.get());
        }

        return autor;
    }

    public Optional<Autor> atualizarAutorCom(Long id, Autor autor) {
        Optional<Autor> retorno = LivroComId(id);

        if (retorno.isPresent()) {
            this.repositorio.update(autor);
        }

        return Optional.of(autor);
    }
}
