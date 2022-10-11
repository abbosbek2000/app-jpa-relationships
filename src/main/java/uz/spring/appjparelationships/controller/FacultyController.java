package uz.spring.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.spring.appjparelationships.entity.Faculty;
import uz.spring.appjparelationships.entity.University;
import uz.spring.appjparelationships.payload.FacultyDTO;
import uz.spring.appjparelationships.repository.FacultyRepository;
import uz.spring.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDTO facultyDTO) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDTO.getName(), facultyDTO.getUniveristyId());
        if (exists)
            return "This university such faculty exist";
        Optional<University> optionalUniversity = universityRepository.findById(facultyDTO.getUniveristyId());
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            Faculty faculty = new Faculty();
            faculty.setName(facultyDTO.getName());
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "successfully added faculty";
        } else {
            return "university not found";
        }
    }

    @GetMapping
    public List<Faculty> facultyList() {
        return facultyRepository.findAll();
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        return facultyRepository.findAllByUniversityId(universityId);
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        } catch (Exception e) {
            return "error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDTO.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDTO.getUniveristyId());
            if (!optionalUniversity.isPresent())
                return "university not found";
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }

}
