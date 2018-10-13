/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.servico;

import br.edu.ifpb.rest.pos.dominio.Livro;
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
public class LivroServico {
    @EJB
    private Repositorio<Livro,String> repositorio;
    public Livro salvar(Livro livro){
       return this.repositorio.salvar(livro);
    }
    public List<Livro> todos(){
        return this.repositorio.list(Livro.class);
    }

    public Optional<Livro> LivroComId(String id) {
        Livro Livro =repositorio.find(Livro.class, id);
       
       return  Optional.of(Livro);
        
             
    }

  
         public Optional<Livro> removerLivroCom(String id) {
        Optional<Livro> livro = LivroComId(id);

        if (livro.isPresent()) {
            this.repositorio.remove(livro.get());
        }

        return livro;
    }

    public Optional<Livro> atualizarLivroCom(String id, Livro livro) {
         Optional<Livro> retorno = LivroComId(id);

        if (retorno.isPresent()) {
            this.repositorio.update(livro);
        }

        return Optional.of(livro);
    }
    }
    

