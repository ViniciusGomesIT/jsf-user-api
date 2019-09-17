package br.com.api.security.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.api.model.MessagePropertiesModel;

public class GenerateMD5 {
	private static final Logger log = LoggerFactory.getLogger(GenerateMD5.class);
	private static final MessagePropertiesModel message = new MessagePropertiesModel();
	  
    public static String generate(String toMD5) {
  
        String generatedPassword = null;
        
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            
            messageDigest.update(toMD5.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            generatedPassword = sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
        	log.error(message.getErrorToGeneratePasswordEncrypted());
        	log.error(Arrays.toString(e.getStackTrace()));
        }
        
        return generatedPassword;
    }
}
