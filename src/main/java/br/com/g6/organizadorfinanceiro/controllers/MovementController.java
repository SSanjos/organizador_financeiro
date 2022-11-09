package br.com.g6.organizadorfinanceiro.controllers;


import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movement")
@CrossOrigin("*")
public class MovementController {

    @Autowired
    private MovementService movementService;


    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Movement>> getAll() {
        try {
            return ResponseEntity.ok(movementService.findAll());
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{typeMovement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<Movement>> findByTypeMovement(@PathVariable("typeMovement") String typeMovement) {
        try {
            return ResponseEntity.ok(movementService.findByTypeMovement(typeMovement));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/save")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Movement> post(@RequestBody Movement createMovement) {
        return ResponseEntity.status(HttpStatus.CREATED)
                //STATUS DE QUE FOI CRIADO
                .body(movementService.save(createMovement));
    }


//    @PatchMapping("/change/{idMovement}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<Movement> put( @PathVariable ("idMovement") Long idMovement,
//                                         @RequestParam("movement") Movement movement){
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(movementService.save(movement));
//
//    }
////    @PathVariable ("idMovement") Long idMovement,
//    @RequestParam("movement") Movement movement

    @PutMapping("/{descriptionMovement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Movement> put(@RequestBody Movement idMovement) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movementService.save(idMovement));

    }

    @DeleteMapping("/{idMovement}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Long> DeleteMovement(@PathVariable Long idMovement){
        movementService.deleteById(idMovement);
        return new ResponseEntity<Long>(idMovement, HttpStatus.OK);


    }

//
//    @PutMapping(value = "/{descriptionMovement}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<List<Movement>> findAllByDescriptionMovementContainingIgnoreCase(@PathVariable("descriptionMovement")@RequestBody String descriptionMovement) {
//        try {
//
//
//            return new ResponseEntity.ok(new )
//
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }

//    @GetMapping("/{typeMovement}")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<List<Movement>> findByTypeMovement(@PathVariable("typeMovement") String typeMovement) {
//        try {
//            return ResponseEntity.ok(movementService.findByTypeMovement(typeMovement));
//        } catch (Exception e) {
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}



