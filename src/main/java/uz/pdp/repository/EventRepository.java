package uz.pdp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EventRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public EventEntity addEventTicket(EventEntity event) {

        List<TicketEntity> tickets = new ArrayList<>();
        for (int i = 0; i < event.getAvailableSeats(); i++) {
            tickets.add(TicketEntity.builder()
                    .locationName(event.getLocationName())
                    .ticketDate(event.getStartTime())
                    .code("#" + Math.random() * 10000)
                    .price(event.getTicketPrice())
                    .event(event)
                    .build());
        }

        event.setTickets(tickets);

        addEvent(event);
        return event;
    }

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

    @Transactional
    public List<EventEntity> showEvent(UUID ownerId) {
        return entityManager.createQuery("from EventEntity e where e.ownerId.id= :id", EventEntity.class)
                .setParameter("id", ownerId).getResultList();
    }

    public EventEntity findById(UUID eventId) {
        return entityManager.find(EventEntity.class, eventId);
    }
}
