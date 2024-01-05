package io.github.rodrigojfagundes.studentdiscipline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.rodrigojfagundes.studentdiscipline.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
