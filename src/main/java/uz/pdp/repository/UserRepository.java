package uz.pdp.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.DTO.SignInDTO;
import uz.pdp.DTO.SignUpDTO;
import uz.pdp.entity.EventEntity;
import uz.pdp.entity.UserEntity;

import java.util.UUID;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity signIn(SignInDTO signInDto) {
        return entityManager.createQuery("from UserEntity where username=:username and password=:password", UserEntity.class)
                .setParameter("username", signInDto.username())
                .setParameter("password", signInDto.password())
                .getSingleResult();
    }

    @Transactional
    public UserEntity signUp(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;
    }

    public UserEntity findById(UUID userId) {
        return entityManager.find(UserEntity.class, userId);
    }

    public void updatePlusBalance(UUID id, Double price) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        userEntity.setBalance(userEntity.getBalance() + price);
        entityManager.merge(userEntity);
    }


    public void updateMinusBalance(UUID id, Double price) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        userEntity.setBalance(userEntity.getBalance() - price);
        entityManager.merge(userEntity);
    }
}
