package it.bitrock.springsecurityjwtdemo.controller;

import it.bitrock.springsecurityjwtdemo.dto.CurriculumDto;
import it.bitrock.springsecurityjwtdemo.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurriculumController {

    @Autowired
    CurriculumService curriculumService;

    @GetMapping("/curriculum")
    public ResponseEntity<List<CurriculumDto>> getAllCurricula() {
        return curriculumService.getAllCurricula();
    }

    @GetMapping("/curriculum/{curriculumId}")
    public ResponseEntity<CurriculumDto> getCurriculum(@PathVariable("curriculumId") Long curriculumId) {
        return curriculumService.getCurriculum(curriculumId);
    }

    @DeleteMapping("/curriculum/{curriculumId}")
    public ResponseEntity<String> deleteCurriculum(@PathVariable("curriculumId") Long curriculumId) {
        return curriculumService.deleteCurriculum(curriculumId);
    }
}
