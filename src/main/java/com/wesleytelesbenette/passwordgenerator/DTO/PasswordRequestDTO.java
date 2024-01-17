package com.wesleytelesbenette.passwordgenerator.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class PasswordRequestDTO
{
    @NotNull
    @Range(min = 0, max = 3, message = "O nível de segurança, deve ser entre 0 e 3.")
    int securityLevel;

    @NotNull
    @Range(min = 8, message = "A quantidade de caracteres deve ser no mínimo 8.")
    int amountCharacters;

    String keyword;
}
