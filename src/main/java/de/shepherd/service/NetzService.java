package de.shepherd.service;

import de.shepherd.entity.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class NetzService {

    @PersistenceContext(unitName = "GhostNetPU")
    private EntityManager em;

    @Transactional
    public void erfasseNetz(Geisternetz netz, MeldendePerson person) {
        if (person != null) {
            em.persist(person);
            netz.setMeldendePerson(person);
        }
        em.persist(netz);
    }

    @Transactional
    public boolean eintragen(Long netzId, String name, String telefon) {
        Geisternetz netz = em.find(Geisternetz.class, netzId);
        if (netz == null) return false;

        BergendePerson person = new BergendePerson();
        person.setName(name);
        person.setTelefonnummer(telefon);
        em.persist(person);

        boolean ok = netz.eintragen(person);
        if (!ok) return false;

        em.merge(netz);
        return true;
    }

    public List<Geisternetz> getOffeneNetze() {
        return em.createQuery(
                        "SELECT g FROM Geisternetz g WHERE g.status IN :statuses",
                        Geisternetz.class
                )
                .setParameter("statuses", List.of(Status.GEMELDET, Status.BERGUNG_BEVORSTEHEND))
                .getResultList();
    }

    @Transactional
    public void meldeGeborgen(Long netzId) {
        Geisternetz netz = em.find(Geisternetz.class, netzId);
        if (netz == null) return;

        if (netz.geborgenMelden()) {
            em.merge(netz);
        }
    }

    @Transactional
    public void meldeVerschollen(Long netzId) {
        Geisternetz netz = em.find(Geisternetz.class, netzId);
        if (netz == null) return;

        netz.verschollenMelden();
        em.merge(netz);
    }
}