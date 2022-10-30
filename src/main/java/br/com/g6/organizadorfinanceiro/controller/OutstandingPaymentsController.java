package br.com.g6.organizadorfinanceiro.controller;

import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.service.OutstandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movement/outstanding")
@CrossOrigin("*")
public class OutstandingPaymentsController {

    @Autowired(required = true)
    OutstandingService outstandingService;

    @PostMapping
    public ResponseEntity<OutstandingPayments> createOutstanding(@RequestBody OutstandingPayments outstandingPayments) {
        try {
            OutstandingPayments savedOutstanding = outstandingService.createdOutstanding(outstandingPayments);



            return new ResponseEntity<OutstandingPayments>(savedOutstanding, HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

}
