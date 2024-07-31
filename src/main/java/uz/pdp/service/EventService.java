package uz.pdp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.DTO.EventDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.TicketEntity;
import uz.pdp.entity.UserEntity;
import uz.pdp.entity.type.EventType;
import uz.pdp.repository.EventRepository;
import uz.pdp.repository.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventService {

    EventRepository eventRepository;
    UserService userService;
    TicketRepository ticketRepository;


    public void addEvent(EventDTO eventDTO) {

        if (!eventDTO.getStartTime().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("The start time must be set in the future\n");
        }
        if (!eventDTO.getEndTime().isAfter(eventDTO.getStartTime())) {
            throw new RuntimeException("The end time must be after the start time");
        }
        int start = eventDTO.getStartTime().getHour() * 60 + eventDTO.getStartTime().getMinute();
        int end = eventDTO.getEndTime().getHour() * 60 + eventDTO.getEndTime().getMinute();

        if (start > end) {

        }

        List<EventEntity> events = getEvents();

        for (EventEntity event : events) {
            if (event.getLocationName().equals(eventDTO.getLocationName())) {

            }
        }

        for (EventEntity event : events) {

        }

        eventRepository.addEventTicket(EventEntity.builder()
                .type(eventDTO.getEventType())
                .locationName(eventDTO.getLocationName())
                .locationPrice(eventDTO.getLocationPrice())
                .capacity(eventDTO.getCapacity() == null ? 0 : eventDTO.getCapacity())
                .ticketPrice(eventDTO.getTicketPrice())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .picture(getPictureByEvent(eventDTO.getEventType()))
                .availableSeats(eventDTO.getCapacity() == null ? 0 : eventDTO.getCapacity())
                .ownerId(eventDTO.getUserId())
                .build());
        userService.updateMinusBalance(eventDTO.getUserId().getId(), 500 + eventDTO.getLocationPrice());
    }

    private static String getPictureByEvent(EventType eventType) {
        return eventType.toString().toLowerCase().concat(".png");
    }

    public void deleteEvent(UUID eventId) {
        eventRepository.deleteEvent(eventId);
    }

    public List<EventEntity> getEvents() {
        return eventRepository.showEvent();
    }

    public List<EventEntity> getEvents(UUID ownerId) {
        return eventRepository.showEvent(ownerId);
    }


    public EventEntity findById(UUID eventId) {
        return eventRepository.findById(eventId);
    }
}
