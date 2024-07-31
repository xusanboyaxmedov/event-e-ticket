package uz.pdp.DAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import uz.pdp.entity.type.EventType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TicketDao {
    private EventType eventType;
    private String locationName;
    private LocalDateTime start;
    private String code;
}
