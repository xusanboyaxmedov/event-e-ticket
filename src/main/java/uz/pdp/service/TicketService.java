package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.DAO.TicketDao;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;
import uz.pdp.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public TicketEntity findByEventId(UUID eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    @Transactional
    public void update(TicketEntity byEventId) {
        ticketRepository.update(byEventId);
    }

    @Transactional
    public void add(TicketEntity ticketEntity) {
        ticketEntity.setCode("#" + Math.random() * 10000 + 1);
        ticketRepository.save(ticketEntity);
    }

    public List<TicketDao> getTickets(UUID userId) {
        List<TicketEntity> ticketEntities = ticketRepository.showTickets(userId);
        List<TicketDao> ticketDaos = new ArrayList<>();
        for (TicketEntity ticketEntity : ticketEntities) {
            TicketDao ticketDao = new TicketDao(ticketEntity.getEvent().getType(), ticketEntity.getLocationName(), ticketEntity.getTicketDate(), ticketEntity.getCode());
            ticketDaos.add(ticketDao);
        }
        return ticketDaos;
    }
}
