package uz.spring.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.spring.appjparelationships.entity.Subject;
import uz.spring.appjparelationships.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;
    //CREATE
    @PostMapping("/add")
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (!existsByName) {
            subjectRepository.save(subject);
            return "subject added";
        }
        return "this subject already exist";
    }

    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }
}
