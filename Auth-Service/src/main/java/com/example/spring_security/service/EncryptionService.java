package com.example.spring_security.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final int GCM_IV_LENGTH = 12;       // 96-bit IV
    private static final int GCM_TAG_LENGTH = 128;     // bits
    private static final String aesKey="646D3B5AE20DB80A3756361ED7B550EE9D1A16E167DF879CA0A94252CE21F224";
    private final SecretKey secretKey;

    public EncryptionService() {
        // IMPORTANT: Load from env variable or Vault for production
        byte[] decodedKey = Base64.getDecoder().decode(aesKey);
        this.secretKey = new SecretKeySpec(decodedKey, "AES");
    }

    // ------------------ ENCRYPT ------------------
    public String encrypt(String plainText) throws Exception {
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH, iv));

        byte[] encrypted = cipher.doFinal(plainText.getBytes());

        // Attach IV + ciphertext
        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getUrlEncoder().encodeToString(combined);
    }

    // ------------------ DECRYPT ------------------
    public String decrypt(String encryptedText) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(encryptedText);

        byte[] iv = new byte[GCM_IV_LENGTH];
        byte[] ciphertext = new byte[decoded.length - GCM_IV_LENGTH];

        System.arraycopy(decoded, 0, iv, 0, GCM_IV_LENGTH);
        System.arraycopy(decoded, GCM_IV_LENGTH, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(GCM_TAG_LENGTH, iv));

        byte[] decrypted = cipher.doFinal(ciphertext);
        return new String(decrypted);
    }
}