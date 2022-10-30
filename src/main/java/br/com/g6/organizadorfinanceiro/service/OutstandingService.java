package br.com.g6.organizadorfinanceiro.service;

import br.com.g6.organizadorfinanceiro.controller.OutstandingPaymentsController;
import br.com.g6.organizadorfinanceiro.model.Movement;
import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.repository.OutstandingPaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OutstandingService {

    @Autowired(required = true)
    private OutstandingPaymentsRepository outstandingPaymentsRepository;



    public OutstandingPayments createdOutstanding(OutstandingPayments outstandingPayments) {

        return outstandingPaymentsRepository.save(outstandingPayments);

    }
}






