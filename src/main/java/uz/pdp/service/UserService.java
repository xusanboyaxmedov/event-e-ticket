package uz.pdp.service;

import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.DTO.SignInDTO;
import uz.pdp.DTO.SignUpDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.UserEntity;
import uz.pdp.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {

    UserRepository userRepository;

    public UserEntity signIn(SignInDTO signInDto) {
        return userRepository.signIn(signInDto);
    }

    public UserEntity signUp(SignUpDTO signUpDTO) {
        return userRepository.signUp(UserEntity.builder()
                .name(signUpDTO.name())
                .username(signUpDTO.username())
                .password(signUpDTO.password())
                .role(signUpDTO.role())
                .balance(1000.0)
                .build());
    }

    public UserEntity findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public void updatePlusBalance(UUID id, Double price) {
        userRepository.updatePlusBalance(id, price);
    }

    @Transactional
    public void updateMinusBalance(UUID id, Double price) {
        userRepository.updateMinusBalance(id, price);
    }
}

