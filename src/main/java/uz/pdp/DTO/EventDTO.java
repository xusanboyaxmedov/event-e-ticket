package uz.pdp.DTO;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.university.entity.EventType;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private EventType eventType;
    private String locationName;
    private Double locationPrice;
    private Integer capacity;
    private Double ticketPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
