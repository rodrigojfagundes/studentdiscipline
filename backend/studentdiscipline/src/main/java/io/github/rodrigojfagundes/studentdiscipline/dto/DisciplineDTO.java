package io.github.rodrigojfagundes.studentdiscipline.dto;

import java.io.Serializable;

import io.github.rodrigojfagundes.studentdiscipline.entities.Discipline;

public class DisciplineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	
	public DisciplineDTO() {}
	
	
	public DisciplineDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public DisciplineDTO(Discipline entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
