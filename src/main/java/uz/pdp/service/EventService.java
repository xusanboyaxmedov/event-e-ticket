package uz.pdp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.DTO.EventDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.type.EventType;
import uz.pdp.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventService {

    EventRepository eventRepository;

    public EventEntity addEvent(EventDTO eventDTO) {

        if (!eventDTO.getStartTime().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Start time should be in future");
        }
        if (!eventDTO.getEndTime().isAfter(eventDTO.getStartTime())) {
            throw new RuntimeException("End time should be after start time");
        }
        int start = eventDTO.getStartTime().getHour() * 60 + eventDTO.getStartTime().getMinute();
        int end = eventDTO.getEndTime().getHour() * 60 + eventDTO.getEndTime().getMinute();
        if (start > end) {}

            List<EventEntity> events = getEvents();
        for (EventEntity event : events) {
            if (event.getLocationName().equals(eventDTO.getLocationName())) {

            }
        }

        for (EventEntity event : events) {

        }

        return eventRepository.addEvent(EventEntity.builder()
                .type(eventDTO.getEventType())
                .locationName(eventDTO.getLocationName())
                .locationPrice(eventDTO.getLocationPrice())
                .capacity(eventDTO.getCapacity())
                .ticketPrice(eventDTO.getTicketPrice())
                .startTime(eventDTO.getStartTime())
                .endTime(eventDTO.getEndTime())
                .picture(getPictureByEvent(eventDTO.getEventType()))
                .availableSeats(eventDTO.getCapacity())
                .build());
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

    public EventEntity findById(UUID eventId) {
        return eventRepository.findById(eventId);
    }

}
