package br.com.api.enums;

import java.util.Arrays;

public enum PhoneTypeEnum {
	
	PESSOAL("PESSOAL", "P"),
	RESIDENCIAL("RESIDENCIAL", "R"),
	EMPRESARIAL("EMPRESARIAL", "E"),
	OUTRO("OUTRO", "O");
	
	private final String name;
	private final String type;
	
	private PhoneTypeEnum(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public PhoneTypeEnum getValueFromType(String type) {
		return Arrays.stream(PhoneTypeEnum.values())
				.filter( phoneType ->  phoneType.getType().equalsIgnoreCase(type))
				.findFirst()
				.orElse(null);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
}
