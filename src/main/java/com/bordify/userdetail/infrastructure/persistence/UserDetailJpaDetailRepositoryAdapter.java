package com.bordify.userdetail.infrastructure.persistence;

import com.bordify.shared.domain.PageResult;
import com.bordify.shared.domain.PaginationRequest;
import com.bordify.userdetail.domain.UserDetail;
import com.bordify.userdetail.domain.UserDetailRepository;
import com.bordify.userdetail.infrastructure.mapper.UserDetailMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDetailJpaDetailRepositoryAdapter implements UserDetailRepository {

    private final UserDetailJpaRepository userDetailJpaRepository;

    public UserDetailJpaDetailRepositoryAdapter(UserDetailJpaRepository userDetailJpaRepository) {
        this.userDetailJpaRepository = userDetailJpaRepository;
    }


    @Override
    public boolean existsByUsername(String userName) {
        return userDetailJpaRepository.existsByUsername(userName);
    }

    @Override
    public Optional<UserDetail> findByUsername(String username) {
        return userDetailJpaRepository.findByUsername(username)
                .map(UserDetailMapper::toDomain);
    }

    @Override
    public void save(UserDetail user) {
        UserDetailEntity userDetailEntity = UserDetailMapper.toEntity(user);
        userDetailJpaRepository.save(userDetailEntity);
    }

    @Override
    public Optional<UserDetail> findById(UUID userId) {
        Optional<UserDetailEntity> userSearched = userDetailJpaRepository.findById(userId);
        return userSearched.map(UserDetailMapper::toDomain);
    }

    @Override
    public PageResult<UserDetail> findAll(PaginationRequest pageable) {

        Pageable pageableResult = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()); // page 0, size 20
        Page<UserDetailEntity> page = userDetailJpaRepository.findAll(pageableResult);

        PageResult<UserDetail> pageResult = new PageResult<UserDetail>(
                page.getContent().stream().map(UserDetailMapper::toDomain).toList(),
                page.getNumber(), page.getSize(), page.getTotalElements());

        return pageResult;
    }

    @Override
    public void delete(UUID id) {
        userDetailJpaRepository.deleteById(id);
    }

}
