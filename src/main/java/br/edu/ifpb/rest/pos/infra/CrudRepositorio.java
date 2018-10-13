/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.rest.pos.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jose
 */
@Stateless
public class CrudRepositorio<T, KEY> implements Repositorio<T, KEY> {

    @PersistenceContext(unitName = "REST-POS_PU")
    private EntityManager em;

    @Override
    public T salvar(T entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public T find(Class<T> tipo, KEY id) {
        return em.find(tipo, id);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public List<T> list(Class<T> tipo) {
        TypedQuery<T> Query
                = em.createQuery("FROM " + tipo.getSimpleName() + " c", tipo);
        return Query.getResultList();

    }

    @Override
    public T remove(T entity) {
        Optional<T> of = Optional.of(entity);
        try {
            em.remove(entity);

        } catch (Exception e) {
        }
        return of.get();
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

}
