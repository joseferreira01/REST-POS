/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.webJsf;

import br.edu.ifpb.rest.pos.dominio.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author jose
 */
@Named
@RequestScoped
public class Controler {
    private Cliente cliente = new Cliente();
    
     public List<Livro> getLivros() {
        //return cliente.lerLivro();
        ArrayList<Livro> list = new ArrayList<>();
                list.add(new Livro("moderna", "java", "java 10"));
                list.add(new Livro("flamengo", "copa do BR", "não deu"));
                list.add(new Livro("moderna", "PHP", "como progamar"));
                return list;
  }
    
}
