package com.example.demo.repositories;

import com.example.demo.entities.Contract;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ContractRepo extends CrudRepository<Contract,Integer> {

    @Query(value =
            "select * from contract\n" +
                    "where client_id = :id", nativeQuery = true)
    Optional<Contract> getContractOfUser(@Param("id") int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE contract\n" +
            "SET price = :NewPrice\n" +
            "WHERE id = :id", nativeQuery = true)
    void updatePriceContract(@Param("NewPrice") double NewPrice,@Param("id")int id);
}
