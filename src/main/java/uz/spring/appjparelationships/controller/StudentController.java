package uz.spring.appjparelationships.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.spring.appjparelationships.entity.Student;
import uz.spring.appjparelationships.repository.StudentRepository;
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    // 1.vazirlik

    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        // select * from student limit 10 offset 0
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    // 2.university
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId,
                                                     @RequestParam int page) {
        // select * from student limit 10 offset 0
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPageUniversity = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);

        Student student = new Student();
        return studentPageUniversity;
    }
    // 3.Faculty Dekanat
    // 4.Group Tutor


}
