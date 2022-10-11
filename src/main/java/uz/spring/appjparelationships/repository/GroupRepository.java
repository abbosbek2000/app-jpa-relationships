package uz.spring.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.spring.appjparelationships.entity.GroupUniversity;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupUniversity, Integer> {
    boolean existsByName(String name);

//    //JPA orqali
//    List<GroupUniversity> findAllByFacultyUniversityId(Integer faculty_university_id);
//
//    @Query("select gr from guruh gr where gr.faculty.university.id =: universityId")
//    List<GroupUniversity> getGroupByUniversityId(Integer universityId);
//
//    @Query(value = "select *\n" +
//            "from guruh g\n" +
//            "         join faculty f on f.id = g.faculty_id\n" +
//            "         join university u on u.id = f.university_id\n" +
//            "where u.id = :universityId;\n", nativeQuery = true)
//    List<GroupUniversity> getGroupUniversityNative(Integer universityId);
}
