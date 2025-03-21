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
    @Column(columnDefinition = "int default 0")
    private Integer availableSeats;
    private String picture;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity ownerId;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<TicketEntity> tickets;
}
