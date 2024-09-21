package com.bordify.auth.infrastructure.persistence;

import com.bordify.auth.domain.UserReadModel;
import com.bordify.auth.domain.UserReadModelRepository;
import com.bordify.shared.domain.UserUserName;
import com.bordify.auth.infrastructure.mapper.UserReadModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.bordify.auth.infrastructure.mapper.UserReadModelMapper.toEntity;


@Repository
@AllArgsConstructor
public class UserReadModelRepositoryJpaAdapter implements UserReadModelRepository {

    private final UserReadRepositoryJpa repository;

    @Override
    public void save(UserReadModel user) {
        repository.save(toEntity(user));
    }

    @Override
    public Optional<UserReadModel> findByUsername(UserUserName username) {
        return repository.findByUserName(username.value())
                .map(UserReadModelMapper::toDomain);
    }

//    @Override
//    public boolean existsByUsername(UserUserName username) {
//        return repository.existsByUserName(username.value());
//    }
//
//    @Override
//    public void save(UserWriteModel user) {
//        repository.save(toEntity(user));
//    }
//
//    @Override
//    public Optional<UserWriteModel> findByUsername(UserUserName username)  {
//        return repository.findByUserName(username.value())
//                .map(UserWriteModelMapper::toDomain);
//    }
//
//    @Override
//    public boolean existsByEmail(UserEmail email) {
//        return repository.existsByEmail(email.value());
//    }
//
//    @Override
//    public Stream<UserWriteModel> findByEmail(UserEmail email) {
//        return repository.findByEmail(email.value()).stream()
//                .map(UserWriteModelMapper::toDomain);
//    }

}
