package it.bitrock.springsecurityjwtdemo.mapper;

import it.bitrock.springsecurityjwtdemo.dto.CurriculumDto;
import it.bitrock.springsecurityjwtdemo.dto.LoginDto;
import it.bitrock.springsecurityjwtdemo.model.Curriculum;
import it.bitrock.springsecurityjwtdemo.repository.AccountRepository;
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
