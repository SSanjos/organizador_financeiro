package br.com.g6.organizadorfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;

@Repository
public interface OutstandingPaymentsRepository extends JpaRepository<OutstandingPayments, Long>{}
