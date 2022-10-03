package it.bitrock.springsecurityjwtdemo.repository;

import it.bitrock.springsecurityjwtdemo.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findAll();

    boolean existsById(Long curriculumId);

    Curriculum getCurriculumById(Long curriculumId);

    void deleteById(Long curriculumId);
}
