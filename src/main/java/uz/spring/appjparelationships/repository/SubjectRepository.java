package uz.spring.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.appjparelationships.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);

}
