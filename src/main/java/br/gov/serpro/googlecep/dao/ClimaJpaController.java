/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.serpro.googlecep.dao;

import br.gov.serpro.googlecep.dao.exceptions.NonexistentEntityException;
import br.gov.serpro.googlecep.entity.Clima;
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
public class ClimaJpaController implements Serializable {

    public ClimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ClimaJpaController() {
         emf = createEntityManagerFactory("PU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clima clima) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clima clima) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clima = em.merge(clima);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clima.getId();
                if (findClima(id) == null) {
                    throw new NonexistentEntityException("The clima with id " + id + " no longer exists.");
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
            Clima clima;
            try {
                clima = em.getReference(Clima.class, id);
                clima.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clima with id " + id + " no longer exists.", enfe);
            }
            em.remove(clima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clima> findClimaEntities() {
        return findClimaEntities(true, -1, -1);
    }

    public List<Clima> findClimaEntities(int maxResults, int firstResult) {
        return findClimaEntities(false, maxResults, firstResult);
    }

    private List<Clima> findClimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clima.class));
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

    public Clima findClima(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clima.class, id);
        } finally {
            em.close();
        }
    }

    public int getClimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clima> rt = cq.from(Clima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
