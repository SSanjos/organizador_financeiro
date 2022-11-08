package br.com.g6.organizadorfinanceiro.controller.dto;

import br.com.g6.organizadorfinanceiro.enumeration.TypeMovement;
import lombok.*;

import javax.persistence.Embeddable;
import java.util.Date;

@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class CreateMovement {


    private TypeMovement typeMovement;

    private double valueMovement;

    private Date dateMovement;

    private String descriptionMovement;

    private Long idUser;

    private String userName;

    private String password;
}
