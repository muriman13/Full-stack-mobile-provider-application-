package com.example.demo.repsitories;

import com.example.demo.entities.Providers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProvidersRepo extends CrudRepository<Providers,Integer> {
   Optional<Providers> findById(int id);

    @Transactional
    @Modifying
    @Query(value="UPDATE channel\n" +
            "SET price = price + (price*(:percent/100))\n" +
            "WHERE providers_id = :id",nativeQuery = true)
    void updatePrice(@Param("id") int id,@Param("percent") double percent);
}
