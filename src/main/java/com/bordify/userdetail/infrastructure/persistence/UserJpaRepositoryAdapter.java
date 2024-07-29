package com.bordify.userdetail.infrastructure.persistence;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserRepository;
import com.bordify.userdetail.infrastructure.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserJpaRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserJpaRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public boolean existsByUsername(String userName) {
        return userJpaRepository.existsByUsername(userName);
    }

    @Override
    public Optional<UserDetail> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public void save(UserDetail user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        userJpaRepository.save(userEntity);
    }

    @Override
    public Optional<UserDetail> findById(UUID userId) {
        Optional<UserEntity> userSearched = userJpaRepository.findById(userId);
        return userSearched.map(UserMapper::toDomain);
    }

    @Override
    public PageResult<UserDetail> findAll(PaginationRequest pageable) {

        Pageable pageableResult = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); // page 0, size 20
        Page<UserEntity> page = userJpaRepository.findAll(pageableResult);

        PageResult<UserDetail> pageResult = new PageResult<UserDetail>(
                page.getContent().stream().map(UserMapper::toDomain).toList(),
                page.getNumber(), page.getSize(), page.getTotalElements());

        return pageResult;
    }

    @Override
    public void delete(UUID id) {
        userJpaRepository.deleteById(id);
    }

}
