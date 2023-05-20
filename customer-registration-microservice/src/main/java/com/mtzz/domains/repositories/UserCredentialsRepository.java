package com.mtzz.domains.repositories;

import com.mtzz.domains.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long>
{
    Boolean existsByUsername(String username);
}
