package com.bordify.auth.infrastructure.persistence;

import com.bordify.auth.domain.UserEmail;
import com.bordify.auth.domain.UserUserName;
import com.bordify.auth.domain.UserWriteModel;
import com.bordify.auth.domain.UserWriteModelRepository;
import com.bordify.auth.infrastructure.mapper.UserWriteModelMapper;
import com.bordify.shared.domain.UserUserId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

import static com.bordify.auth.infrastructure.mapper.UserWriteModelMapper.toEntity;

@Repository
@AllArgsConstructor
public class UserWriteModelRepositoryJpaAdapter implements UserWriteModelRepository {

    private final UserWriteRepositoryJpa repository;

    @Override
    public boolean existsByUsername(UserUserName username) {
        return repository.existsByUserName(username.value());
    }

    @Override
    public void save(UserWriteModel user) {
        repository.save(toEntity(user));
    }

    @Override
    public Optional<UserWriteModel> findByUsername(UserUserName username)  {
        return repository.findByUserName(username.value())
                .map(UserWriteModelMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(UserEmail email) {
        return repository.existsByEmail(email.value());
    }

    @Override
    public Stream<UserWriteModel> findByEmail(UserEmail email) {
        return repository.findByEmail(email.value()).stream()
                .map(UserWriteModelMapper::toDomain);
    }

    @Override
    public Optional<UserWriteModel> findById(UserUserId userId) {
        return repository.findById(userId.value())
                .map(UserWriteModelMapper::toDomain);
    }

}
