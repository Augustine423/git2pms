package org.mdt.crewtaskmanagement.repository.entity;

import org.mdt.crewtaskmanagement.model.User;
import org.mdt.crewtaskmanagement.repository.BaseRepository;
import org.mdt.crewtaskmanagement.repository.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    User findByEmail(String email);
    @Query("select u.id from User  u where u.email = ?1")
    Optional<Long> getIdByEmail(String email);
}
