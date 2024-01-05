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
import io.github.rodrigojfagundes.studentdiscipline.entities.Discipline;
import io.github.rodrigojfagundes.studentdiscipline.repositories.DisciplineRepository;
import io.github.rodrigojfagundes.studentdiscipline.services.exceptions.DatabaseException;
import io.github.rodrigojfagundes.studentdiscipline.services.exceptions.ResourceNotFoundException;

@Service
public class DisciplineService {
	
	@Autowired
	private DisciplineRepository repository;
	
	@Transactional(readOnly = true)
	public Page<DisciplineDTO> findAllPaged(PageRequest pageRequest) {
		Page<Discipline> list = repository.findAll(pageRequest);
		return list.map(x -> new DisciplineDTO(x));	
	}
	
	
	@Transactional(readOnly = true)
	public DisciplineDTO findById(Long id) {
		Optional<Discipline> obj = repository.findById(id);
		
		Discipline entity = obj.orElseThrow(()-> new EntityNotFoundException("Entity not found"));
		
		return new DisciplineDTO(entity);
		
	}
	
	@Transactional
	public DisciplineDTO insert(DisciplineDTO dto) {
		
		Discipline entity = new Discipline();
		
		entity.setName(dto.getName());
		
		entity = repository.save(entity);
		return new DisciplineDTO(entity);
		
	}
	
	@Transactional
	public DisciplineDTO update(Long id, DisciplineDTO dto) {
		try {
			Discipline entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			
			return new DisciplineDTO(entity);
			
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}
	
	public void delete (Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}	
}