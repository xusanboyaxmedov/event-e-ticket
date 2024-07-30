package uz.pdp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class EventRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public EventEntity addEventTicket(Integer eventId) {

        EventEntity event = entityManager.find(EventEntity.class, eventId);
        List<TicketEntity> tickets = new ArrayList<>();
        for (Integer i = 0; i < event.getCapacity(); i++) {
            tickets.add(TicketEntity.builder()
                    .code("#" + Math.random() * 10000)
                    .price(event.getTicketPrice())
                    .event(entityManager.find(EventEntity.class, eventId))
                    .build());
        }

        event.setTickets(tickets);

        entityManager.persist(event);
        return event;
    }

    @Transactional
    public EventEntity addEvent(EventEntity eventEntity) {
        entityManager.persist(eventEntity);
        return eventEntity;
    }

    @Transactional
    public void deleteEvent(UUID eventId) {
        EventEntity event = entityManager.find(EventEntity.class, eventId);
        entityManager.remove(event);
    }

    @Transactional
    public List<EventEntity> showEvent() {
        return entityManager.createQuery("from EventEntity", EventEntity.class).getResultList();
    }

    public EventEntity findById(UUID eventId) {
        return entityManager.find(EventEntity.class, eventId);
    }
}
