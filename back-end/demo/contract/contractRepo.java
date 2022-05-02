package com.example.demo.contract;

import com.example.demo.channels.channel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface contractRepo extends CrudRepository<contract,Integer> {

    @Query(value =
            "select * from contract\n" +
                    "where client_id = :id", nativeQuery = true)
    contract getContractOfUser(@Param("id") int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE contract\n" +
            "SET price = :NewPrice\n" +
            "WHERE id = :id", nativeQuery = true)
    void updatePriceContract(@Param("NewPrice") double NewPrice,@Param("id")int id);
}
