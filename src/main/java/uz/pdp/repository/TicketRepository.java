package uz.pdp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class TicketRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public TicketEntity findByEventId(UUID eventId) {

        List<TicketEntity> events = entityManager.createQuery("from TicketEntity t where t.event.id = :event", TicketEntity.class)
                .setParameter("event", eventId).getResultList();
        for (TicketEntity event : events) {
            if(event.getBuyer() == null) return event;
        }

        return null;
    }

    public void update(TicketEntity byEventId) {
        TicketEntity ticketEntity = entityManager.find(TicketEntity.class, byEventId.getId());
        ticketEntity.setBuyer(byEventId.getBuyer());
        entityManager.merge(ticketEntity);
    }

    public void save(TicketEntity ticketEntity) {
        entityManager.persist(ticketEntity);
    }

    public List<TicketEntity> showTickets(UUID userId) {
        return entityManager.createQuery("from TicketEntity e where e.buyer.id= :id", TicketEntity.class)
                .setParameter("id", userId).getResultList();
    }
}
