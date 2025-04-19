package com.example.qtsapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.qtsapp.entities.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
	
}
