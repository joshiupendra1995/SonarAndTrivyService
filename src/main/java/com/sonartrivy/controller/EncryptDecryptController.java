package com.sonartrivy.controller;

import com.sonartrivy.util.CryptoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password")
public class EncryptDecryptController {

    private static final String SECRET_KEY = "my-secret-password";

    @GetMapping("/encrypt")
    public byte[] encrypt() throws Exception {
        return CryptoUtil.encrypt(SECRET_KEY);
    }
}

