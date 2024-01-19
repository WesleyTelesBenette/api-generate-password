package com.wesleytelesbenette.generatepassword.controllers;

import com.wesleytelesbenette.generatepassword.dtos.PasswordRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RequestMapping("/password")
public class PasswordController
{
    private final Map<Integer,String> passwordCharacters;

    public PasswordController()
    {
        this.passwordCharacters = new HashMap<>();
        this.passwordCharacters.put(0, "0123456789");
        this.passwordCharacters.put(1, "abcdefghijklmnopqrstuvwxyz");
        this.passwordCharacters.put(2, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.passwordCharacters.put(3, "!@#$%&*().,>:{}_-+=/\\|");
    }

    @PostMapping
    public ResponseEntity<String> getGeneratePassword(@RequestBody @Valid PasswordRequestDTO request)
    {
        final String password = generatePassword
        (
            request.getSecurityLevel(),
            request.getAmountCharacters(),
            request.getKeyword()
        );

        String jsonResponse = "{ \"password\": \"" + password + "\" }";
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    private String generatePassword(int securityLevel, int amountCharacters, String keyword)
    {
        Random random = new Random();

        String key = (keyword.length() > 0) ? keyword : "";
        int charsGenerate = amountCharacters - key.length();
        int keyPosition = random.nextInt(charsGenerate + 1);

        String generateResponse = (charsGenerate == 0) ? key : "";

        for (int c = 0; c < charsGenerate; c++)
        {
            if (c == keyPosition) generateResponse = generateResponse.concat(key);

            String currentString = passwordCharacters.get(c % (1 + securityLevel));
            char currentChar = currentString.charAt(random.nextInt(currentString.length()));
            generateResponse = generateResponse.concat(String.valueOf(currentChar));

            if ((keyPosition == charsGenerate) && (c+1 == charsGenerate))
                generateResponse = generateResponse.concat(key);
        }

        return generateResponse;
    }
}