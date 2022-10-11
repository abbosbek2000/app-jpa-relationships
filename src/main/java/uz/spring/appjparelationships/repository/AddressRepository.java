package uz.spring.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.spring.appjparelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
