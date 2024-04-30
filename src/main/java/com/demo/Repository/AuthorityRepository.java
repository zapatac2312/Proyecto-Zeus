package com.demo.Repository;

import com.demo.Modelo.Authority;
import com.demo.Util.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    // Method to search for a role throw the name/role in our data base
    Optional<Authority> findByName(AuthorityName name);
}
