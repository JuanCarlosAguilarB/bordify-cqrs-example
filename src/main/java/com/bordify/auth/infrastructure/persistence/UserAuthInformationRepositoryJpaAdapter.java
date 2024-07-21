package com.bordify.auth.infrastructure.persistence;

import com.bordify.auth.domain.UserAuthInformation;
import com.bordify.auth.domain.UserAuthInformationRepository;
import com.bordify.auth.infrastructure.mapper.UserAuthInformationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

import static com.bordify.auth.infrastructure.mapper.UserAuthInformationMapper.toEntity;

@Repository
@AllArgsConstructor
public class UserAuthInformationRepositoryJpaAdapter implements UserAuthInformationRepository {

    private final UserAuthInformationRepositoryJpa repository;

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public void save(UserAuthInformation user) {
        repository.save(toEntity(user));
    }

    @Override
    public Optional<UserAuthInformation> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(UserAuthInformationMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Stream<UserAuthInformation> findByEmail(String email) {
        return repository.findByEmail(email).stream()
                .map(UserAuthInformationMapper::toDomain);
    }

}
