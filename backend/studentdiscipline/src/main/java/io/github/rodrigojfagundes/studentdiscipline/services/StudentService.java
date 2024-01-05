package io.github.rodrigojfagundes.studentdiscipline.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.rodrigojfagundes.studentdiscipline.dto.DisciplineDTO;
import io.github.rodrigojfagundes.studentdiscipline.dto.StudentDTO;
import io.github.rodrigojfagundes.studentdiscipline.entities.Discipline;
import io.github.rodrigojfagundes.studentdiscipline.entities.Student;
import io.github.rodrigojfagundes.studentdiscipline.repositories.DisciplineRepository;
import io.github.rodrigojfagundes.studentdiscipline.repositories.StudentRepository;
import io.github.rodrigojfagundes.studentdiscipline.services.exceptions.DatabaseException;
import io.github.rodrigojfagundes.studentdiscipline.services.exceptions.ResourceNotFoundException;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private DisciplineRepository disciplineRepository;
	
	public Page<StudentDTO> findAllPaged(PageRequest pageRequest) {
		
		//TA ESCRITO LIST mas o CORRETO SERIA PAGE
		Page<Student> list = repository.findAll(pageRequest);
		
		return list.map(x -> new StudentDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public StudentDTO findById(Long id) {
		Optional<Student> obj = repository.findById(id);		
		Student entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new StudentDTO(entity, entity.getDisciplines());
	}
	
	@Transactional
	public StudentDTO insert(StudentDTO dto) {
		Student entity = new Student();
		copyDtoToEntity(dto, entity);
		
		entity = repository.save(entity);
		return new StudentDTO(entity);
		
	}
	
	public StudentDTO update(Long id, StudentDTO dto) {
		try {
			Student entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			
			entity = repository.save(entity);
			return new StudentDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	
	private void copyDtoToEntity(StudentDTO dto, Student entity) {
		entity.setName(dto.getName());
		entity.setCity(dto.getCity());
		entity.setDate(dto.getDate());
		entity.setCpf(dto.getCpf());
		entity.setSalary(dto.getSalary());
		
		entity.getDisciplines().clear();
		
		for(DisciplineDTO disDto : dto.getDisciplines()) {
			Discipline discipline = disciplineRepository.getOne(disDto.getId());
			entity.getDisciplines().add(discipline);
		}
		
	}
}