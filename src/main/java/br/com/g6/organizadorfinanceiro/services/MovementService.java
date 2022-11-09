package br.com.g6.organizadorfinanceiro.services;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import br.com.g6.organizadorfinanceiro.models.Movement;
import br.com.g6.organizadorfinanceiro.models.User;
import br.com.g6.organizadorfinanceiro.repository.MovementRepository;
import br.com.g6.organizadorfinanceiro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovementService {
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;


    private Optional<User> getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userLogged = userRepository.findByUsername(userDetails.getUsername());

        return userLogged;
    }

    //~~~~Tratamento de erro~~~~
    public List<Movement> findAll() {
        try {
           Optional<User> user = getCurrentUser();

          return (movementRepository.findByUser(user.get()));
        } catch (Exception e) {
            throw new RuntimeException("Movimento não encontrado" + e.getMessage());
        }
    }

    public Movement save( Movement createMovement) {
        try {
            Optional<User> user = getCurrentUser();
            if(user.isPresent())
            {
                createMovement.setUser(user.get());
                return (movementRepository.save(createMovement));
            }
            return null;
            //createMovement.setUser(userDetails );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar movimento" + e.getMessage());
        }
    }

//~~~GetByEmail

//    public List<Movement> getByUserEmail(String email) {
//
//       Optional<User> usuario = userRepository.findByUserEmail(email);
//       if(usuario.isPresent())
//       {
//           return movementRepository.findByIdUser(usuario.get().getIdUser());
//       }
//
//        return null;
//    }

    //~~~~~~

//    public Optional<Movement> findAll(){
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//
//
//
//        return movementRepository.findById(userDetails.getAuthorities());
//
//
//
//        return null;
//}



 //~~~~Tratamento de erro findAllByDescriptionMovement~~~~
    public  List<Movement> findAllByDescriptionMovementContainingIgnoreCase (String descriptionMovement){
        try {


            return movementRepository.findAllByDescriptionMovementContainingIgnoreCase(descriptionMovement);
        } catch (Exception e) {
            throw new RuntimeException("Essa descrição não existe" + e.getMessage());
        }

    }


    public List<Movement> findByTypeMovement(String typeMovement) {
        try {
            TypeMovement type = TypeMovement.get(typeMovement);
            Optional<User> user = getCurrentUser();
            if (user.isPresent()) {
                return (movementRepository.findByTypeMovement(type.toString(),user.get().getId()));
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conusltar movimentos" + e.getMessage());
        }
    }


}
