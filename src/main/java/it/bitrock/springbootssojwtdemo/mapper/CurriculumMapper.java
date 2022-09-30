package it.bitrock.springbootssojwtdemo.mapper;

import it.bitrock.springbootssojwtdemo.dto.CurriculumDto;
import it.bitrock.springbootssojwtdemo.dto.LoginDto;
import it.bitrock.springbootssojwtdemo.model.Curriculum;
import it.bitrock.springbootssojwtdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CurriculumMapper {
    private CurriculumMapper() {
    }

    @Autowired
    static LoginDto loginDto;

    @Autowired
    static AccountRepository accountRepository;

    public static CurriculumDto fromCurriculumToCurriculumDto(Curriculum curriculum) {
        CurriculumDto curriculumDto = new CurriculumDto();
        curriculumDto.setId(curriculum.getId());
        curriculumDto.setName(curriculum.getName());
        curriculumDto.setSurname(curriculum.getSurname());
        curriculumDto.setFile(curriculum.getFile());
        curriculumDto.setUploadDate(curriculum.getCreatedOn());
        curriculumDto.setStatus(curriculum.getStatus().getName());
        return curriculumDto;
    }
}
