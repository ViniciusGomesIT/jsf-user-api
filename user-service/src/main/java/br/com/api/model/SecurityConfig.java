package br.com.api.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "security")
@Component
public class SecurityConfig implements Serializable {

	private static final long serialVersionUID = 6546946449947083036L;

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
