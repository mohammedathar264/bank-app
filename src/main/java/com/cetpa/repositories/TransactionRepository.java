package com.cetpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cetpa.models.TransactionSummary;

public interface TransactionRepository extends JpaRepository<TransactionSummary,Integer> 
{
	
}
