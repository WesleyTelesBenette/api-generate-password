package com.wesleytelesbenette.generatepassword.dtos;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class PasswordRequestDTO
{
    @Range(min = 0, max = 3, message = "O nível de segurança, deve ser entre 0 e 3.")
    int securityLevel;

    @Range(min = 8, message = "A quantidade de caracteres deve ser no mínimo 8.")
    int amountCharacters = 0;

    @Size(max = 8, message = "A palavra-chave pode ter no máximo 8 caracteres.")
    String keyword;
}