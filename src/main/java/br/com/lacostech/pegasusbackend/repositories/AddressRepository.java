package br.com.lacostech.pegasusbackend.repositories;

import br.com.lacostech.pegasusbackend.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
