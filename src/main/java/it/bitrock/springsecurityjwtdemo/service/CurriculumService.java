package it.bitrock.springsecurityjwtdemo.service;

import it.bitrock.springsecurityjwtdemo.dto.CurriculumDto;
import it.bitrock.springsecurityjwtdemo.mapper.CurriculumMapper;
import it.bitrock.springsecurityjwtdemo.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurriculumService {

    @Autowired
    CurriculumRepository curriculumRepository;

    public ResponseEntity<CurriculumDto> getCurriculum(Long curriculumId) {
        if (curriculumRepository.existsById(curriculumId)) {
            return new ResponseEntity<>(CurriculumMapper.fromCurriculumToCurriculumDto(
                    curriculumRepository.getCurriculumById(curriculumId)),
                    HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<CurriculumDto>> getAllCurricula() {
        return new ResponseEntity<>(curriculumRepository.findAll(Sort.by("createdOn")).stream()
                .map(CurriculumMapper::fromCurriculumToCurriculumDto).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCurriculum(Long curriculumId) {
        if (curriculumRepository.existsById(curriculumId)) {
            curriculumRepository.deleteById(curriculumId);
            return new ResponseEntity<>("Curriculum delete successfully!",
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("BAD REQUEST!", HttpStatus.BAD_REQUEST);
    }
}
