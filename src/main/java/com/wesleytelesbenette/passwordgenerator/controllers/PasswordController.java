package com.wesleytelesbenette.passwordgenerator.controllers;

import com.wesleytelesbenette.passwordgenerator.DTO.PasswordRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
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

    @GetMapping
    public String getGeneratePassword (@RequestBody PasswordRequestDTO request)
    {
        return generatePassword
        (
            request.getSecurityLevel(),
            request.getAmountCharacters(),
            request.getKeyword()
        );
    }

    private String generatePassword(int securityLevel, int amountCharacters, String keyword)
    {
        Random random = new Random();

        String key = (keyword != null) ? keyword : "";
        int charsGenerate = amountCharacters - key.length();
        int keyPosition = random.nextInt(charsGenerate + 1);

        String generateResponse = "";

        for (int c = 0; c < charsGenerate;c++)
        {
            if (c == keyPosition) generateResponse = generateResponse.concat(key);

            String currentString = passwordCharacters.get(c % (1 + securityLevel));
            char currentChar = currentString.charAt(random.nextInt(currentString.length()));
            generateResponse = generateResponse.concat(String.valueOf(currentChar));
        }

        return generateResponse;
    }
}
