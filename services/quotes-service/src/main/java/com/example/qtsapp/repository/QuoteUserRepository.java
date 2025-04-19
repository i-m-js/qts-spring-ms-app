package com.example.qtsapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.qtsapp.entities.QuoteUser;

@Repository
public interface QuoteUserRepository extends CrudRepository<QuoteUser, Long> {
	Optional<QuoteUser> findByUsername(String username);
}
