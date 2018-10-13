/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.infra;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jose
 */
public interface Repositorio<T, KEY> {

    T find(Class<T> tipo, KEY id);

    EntityManager getEm();

    List<T> list(Class<T> tipo);

    T remove(T entity);

    T salvar(T entity);

    T update(T entity);
    
}
