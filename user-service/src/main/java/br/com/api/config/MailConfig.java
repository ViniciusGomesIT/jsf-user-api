package br.com.api.config;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.api.model.EmailProperties;

@ConfigurationProperties
public class MailConfig {

	@Inject
	private EmailProperties emailConfig;
	
	@Inject
	private Environment env;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost(env.getProperty("mail.smtp.host"));
		mailSender.setPort(env.getProperty("mail.smtp.port", Integer.class));
		mailSender.setUsername(env.getProperty("mail.smtp.username"));
		mailSender.setPassword(env.getProperty("mail.smtp.password"));

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.connectionTimeout", 10000);
//		props.put("mail.smtp.starttls.required", emailConfig.getStarttlsRequired());
//		props.put("mail.smtp.ssl.enable", emailConfig.getSslEnable());
//		properties.put("mail.smtp.socketFactory.port", "465");  
//		properties.put("mail.smtp.socketFactory.fallback", "false");  
//		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		mailSender.setJavaMailProperties(props);

		return mailSender;
	}
}
