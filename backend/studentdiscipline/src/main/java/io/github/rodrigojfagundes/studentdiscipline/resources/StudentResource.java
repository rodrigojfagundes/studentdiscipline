package io.github.rodrigojfagundes.studentdiscipline.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.rodrigojfagundes.studentdiscipline.dto.StudentDTO;
import io.github.rodrigojfagundes.studentdiscipline.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {
	
	@Autowired
	private StudentService service;
	
	@GetMapping
	public ResponseEntity<Page<StudentDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12")
			Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
			//TA ESCRITO LIST MAS NA VERDADE E DO TIPO PAGE
		Page<StudentDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<StudentDTO> findById(@PathVariable Long id){
		StudentDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto);
	}
	
	
	@PostMapping
	public ResponseEntity<StudentDTO> insert (@RequestBody StudentDTO dto) {
		dto = service.insert(dto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping
	public ResponseEntity<StudentDTO> update (@PathVariable Long id, @RequestBody StudentDTO dto) {
		dto = service.update(id, dto);
		
		return ResponseEntity.ok().body(dto);
	}
	
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}