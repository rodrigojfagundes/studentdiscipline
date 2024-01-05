package io.github.rodrigojfagundes.studentdiscipline.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.github.rodrigojfagundes.studentdiscipline.entities.Discipline;
import io.github.rodrigojfagundes.studentdiscipline.entities.Student;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String city;
	private Double salary;
	private String cpf;
	private Instant date;	
	
	private List<DisciplineDTO> disciplines = new ArrayList<>();
	
	public StudentDTO() {}

	public StudentDTO(Long id, String name, String city, Double salary, String cpf, Instant date) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.salary = salary;
		this.cpf = cpf;
		this.date = date;
	}
	
	public StudentDTO(Student entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.city = entity.getCity();
		this.salary = entity.getSalary();
		this.cpf = entity.getCpf();
		this.date = entity.getDate();
	}
	
	public StudentDTO(Student entity, Set<Discipline> disciplines) {
		this(entity);
		disciplines.forEach(dis -> this.disciplines.add(new DisciplineDTO(dis)));
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public List<DisciplineDTO> getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(List<DisciplineDTO> disciplines) {
		this.disciplines = disciplines;
	}
	
	
}