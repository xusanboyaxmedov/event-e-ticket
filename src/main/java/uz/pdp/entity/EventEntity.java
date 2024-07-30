package uz.pdp.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.entity.type.EventType;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "events")
public class EventEntity extends BaseEntity{
    private EventType type;
    private String locationName;
    private Double locationPrice;
    private Integer capacity;
    private Double ticketPrice;
    private Integer availableSeats;
    private String picture;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;

}
