package com.lms.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.lms.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    
}
