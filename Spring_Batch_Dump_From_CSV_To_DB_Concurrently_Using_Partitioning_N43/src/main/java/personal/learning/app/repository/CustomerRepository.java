package personal.learning.app.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import personal.learning.app.entity.CustomerDb;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDb, BigInteger> {

}
