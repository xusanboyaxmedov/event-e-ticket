package uz.pdp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;

import java.util.UUID;

@Repository
public class TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public TicketEntity findByEventId(UUID eventId) {
        return (TicketEntity) entityManager.createQuery("from TicketEntity where event.id = :eventId and buyer = null ", TicketEntity.class)
                .setParameter("eventId", eventId).getResultList();
    }

    public void update(TicketEntity byEventId) {
        TicketEntity ticketEntity = entityManager.find(TicketEntity.class, byEventId.getId());
        ticketEntity.setBuyer(byEventId.getBuyer());
        entityManager.merge(ticketEntity);
    }
}
