package br.com.api.security.utils;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bytebuddy.asm.Advice.This;

public class GenerateAES {

	private static final Logger LOGGER = LoggerFactory.getLogger(This.class);
	
	static String IV = "AAAAAAAAAAAAAAAA";

	public static byte[] encrypt(String textopuro, String chaveEncriptacao) {
		Cipher encripta;

		try {
			encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

			SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

			encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

			return encripta.doFinal(textopuro.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error(Arrays.toString(e.getStackTrace()));
		} 
		
		return new byte[0];
	}

	public static String decrypt(byte[] passwordAesEncypted, String chaveEncriptacao) {
		Cipher decripta;
		try {
			decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

			SecretKeySpec key = new SecretKeySpec(chaveEncriptacao.getBytes("UTF-8"), "AES");

			decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

			return new String(decripta.doFinal(passwordAesEncypted), "UTF-8");
		} catch (Exception e) {
			LOGGER.error(Arrays.toString(e.getStackTrace()));
		} 
		
		return null;
	}
}
