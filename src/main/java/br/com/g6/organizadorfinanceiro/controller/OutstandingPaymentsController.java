package br.com.g6.organizadorfinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.g6.organizadorfinanceiro.model.OutstandingPayments;
import br.com.g6.organizadorfinanceiro.service.OutstandingService;

@RestController
@RequestMapping("/movement/outstanding")
@CrossOrigin("*")
public class OutstandingPaymentsController {

    @Autowired(required = true)
    OutstandingService outstandingService;

    @GetMapping
	public ResponseEntity<List<OutstandingPayments>> getAll(){
		try {
			return ResponseEntity.ok(outstandingService.findAll());
		}
		catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
		
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
    
    @PutMapping
	public ResponseEntity<OutstandingPayments> put(@RequestBody OutstandingPayments outstandingPayments){
		return ResponseEntity.ok(outstandingService.createdOutstanding(outstandingPayments));
	}

}
