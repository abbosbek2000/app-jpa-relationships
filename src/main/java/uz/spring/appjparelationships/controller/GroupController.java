package uz.spring.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.spring.appjparelationships.entity.Faculty;
import uz.spring.appjparelationships.entity.GroupUniversity;
import uz.spring.appjparelationships.payload.GroupDTO;
import uz.spring.appjparelationships.repository.FacultyRepository;
import uz.spring.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @GetMapping
    public List<GroupUniversity> getGroups() {
        return groupRepository.findAll();
    }

    //universitet ma'sul hodimi uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<GroupUniversity> getGroupsByUniversityId(@PathVariable Integer universityId) {
        return null;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDTO groupDTO) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDTO.getFacultyId());
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            GroupUniversity group = new GroupUniversity();
            group.setFaculty(faculty);
            group.setName(groupDTO.getName());
            groupRepository.save(group);
            return "saved group";
        }
        return "faculty not found";
    }
}
