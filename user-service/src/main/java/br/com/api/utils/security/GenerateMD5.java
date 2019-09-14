package br.com.api.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.api.controller.model.MessageModel;

public class GenerateMD5 {
	private static final Logger log = LoggerFactory.getLogger(GenerateMD5.class);
	private static final MessageModel message = new MessageModel();
	  
    public static String generate(String toMD5) {
  
        String generatedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toMD5.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            generatedPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
        	log.error(message.getErrorToGeneratePasswordEncrypt());
        	log.error(e.toString());
        }
        
        return generatedPassword;
    }
}
