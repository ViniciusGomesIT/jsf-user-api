package br.com.api.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.mail")
@Component
public class EmailProperties implements Serializable {

	private static final long serialVersionUID = 3397937958734073611L;

	private String host;
	private String port;
	private String username;
	private String password;
	private String emailSender;

	private String testConnection;
	private String protocol;
	private String auth;
	private String connectionTimeout;
	private String sslEnable;
	private String starttlsEnable;
	private String starttlsRequired;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getTestConnection() {
		return testConnection;
	}

	public void setTestConnection(String testConnection) {
		this.testConnection = testConnection;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getSslEnable() {
		return sslEnable;
	}

	public void setSslEnable(String sslEnable) {
		this.sslEnable = sslEnable;
	}

	public String getStarttlsEnable() {
		return starttlsEnable;
	}

	public void setStarttlsEnable(String starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}

	public String getStarttlsRequired() {
		return starttlsRequired;
	}

	public void setStarttlsRequired(String starttlsRequired) {
		this.starttlsRequired = starttlsRequired;
	}
}
