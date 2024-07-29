package com.bordify.auth.infrastructure.persistence;

import com.bordify.auth.domain.UserReadModel;
import com.bordify.auth.domain.UserRepository;
import com.bordify.auth.domain.UserEmail;
import com.bordify.auth.domain.UserUserName;
import com.bordify.auth.infrastructure.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

import static com.bordify.auth.infrastructure.mapper.UserMapper.toEntity;

@Repository
@AllArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {

    private final UserRepositoryJpa repository;

    @Override
    public boolean existsByUsername(UserUserName username) {
        return repository.existsByUserName(username.value());
    }

    @Override
    public void save(UserReadModel user) {
        repository.save(toEntity(user));
    }

    @Override
    public Optional<UserReadModel> findByUsername(UserUserName username)  {
        return repository.findByUserName(username.value())
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(UserEmail email) {
        return repository.existsByEmail(email.value());
    }

    @Override
    public Stream<UserReadModel> findByEmail(UserEmail email) {
        return repository.findByEmail(email.value()).stream()
                .map(UserMapper::toDomain);
    }

}
