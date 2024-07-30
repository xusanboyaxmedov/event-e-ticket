package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;
import uz.pdp.repository.TicketRepository;

import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    public TicketEntity findByEventId(UUID eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public void update(TicketEntity byEventId) {
        ticketRepository.update(byEventId);
    }
}
