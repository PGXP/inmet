/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.serpro.googlecep.dao;

import br.gov.serpro.googlecep.dao.exceptions.NonexistentEntityException;
import br.gov.serpro.googlecep.entity.Estacoes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 70744416353
 */
public class EstacoesJpaController implements Serializable {

    public EstacoesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EstacoesJpaController() {
        emf = createEntityManagerFactory("PU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estacoes estacoes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estacoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estacoes estacoes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estacoes = em.merge(estacoes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estacoes.getId();
                if (findEstacoes(id) == null) {
                    throw new NonexistentEntityException("The estacoes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estacoes estacoes;
            try {
                estacoes = em.getReference(Estacoes.class, id);
                estacoes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estacoes with id " + id + " no longer exists.", enfe);
            }
            em.remove(estacoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estacoes> findEstacoesEntities() {
        return findEstacoesEntities(true, -1, -1);
    }

    public List<Estacoes> findEstacoesEntities(int maxResults, int firstResult) {
        return findEstacoesEntities(false, maxResults, firstResult);
    }

    private List<Estacoes> findEstacoesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estacoes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Estacoes findEstacoes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estacoes.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstacoesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estacoes> rt = cq.from(Estacoes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
