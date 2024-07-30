package uz.pdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.security.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity{

    private Double price;
    private String locationName;
    private LocalDateTime ticketDate;
    private String code;

    @ManyToOne
    private EventEntity event;
    @ManyToOne
    private UserEntity buyer;
}
