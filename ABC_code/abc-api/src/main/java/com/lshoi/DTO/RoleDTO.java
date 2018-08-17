package com.lshoi.DTO;

public enum RoleDTO {
	CONSUMER(0), PRODUCER(1), ADMIN(2), MODERATO(3);
	private int value;
	private RoleDTO(int value) {
		this.value = value;
	}
	
	public RoleDTO getRole(int value) {
		if (value == 0) {
			return RoleDTO.CONSUMER;
		} else if (value == 1) {
			
		}
		return RoleDTO.CONSUMER;
	}
}