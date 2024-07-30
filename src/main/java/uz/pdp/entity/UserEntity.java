package uz.pdp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import uz.pdp.entity.type.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    private String name;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private UserType role;
    private Double balance;
}
