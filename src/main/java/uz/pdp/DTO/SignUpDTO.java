package uz.pdp.DTO;

import uz.pdp.entity.type.UserType;

public record SignUpDTO(String name, String username, String password, UserType role) {
}
