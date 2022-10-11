package uz.spring.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.spring.appjparelationships.entity.Address;
import uz.spring.appjparelationships.entity.University;
import uz.spring.appjparelationships.payload.UniversityDTO;
import uz.spring.appjparelationships.repository.AddressRepository;
import uz.spring.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //READ
    @RequestMapping(value = "/university/get", method = RequestMethod.GET)
    public List<University> getUniversityList() {
        return universityRepository.findAll();
    }

    //CRATE
    @RequestMapping(value = "/university/add", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDTO universityDTO) {
        Address address = new Address();
        address.setCity(universityDTO.getCity());
        address.setDistrict(universityDTO.getDistrict());
        address.setStreet(universityDTO.getStreet());
        Address saveAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDTO.getName());
        university.setAddress(saveAddress);
        universityRepository.save(university);

        return "University added";
    }

    //update

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDTO universityDTO) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            university.setName(universityDTO.getName());
            Address address = university.getAddress();
            address.setStreet(universityDTO.getStreet());
            address.setDistrict(universityDTO.getDistrict());
            address.setCity(universityDTO.getCity());
            addressRepository.save(address);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "University deleted";
    }
}
