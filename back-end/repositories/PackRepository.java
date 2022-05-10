package com.example.demo.repositories;

import com.example.demo.entities.Pack;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PackRepository extends CrudRepository<Pack, Integer> {




    void deleteByname(String name);
    @Modifying
    @Query("update Pack u set u.name = :name where u.id = :id")
    Pack updatePack(@Param(value  = "name")String name,@Param(value = "id")Integer id);

    @Query(value =
            "select * from packages\n" +
                    "join contract on packages.contract_id = :id\n" +
                    "group by packages.id", nativeQuery = true)
    Optional<List<Pack>> getInContract(@Param("id") Integer id);

    Optional<Pack> findByname(String category);
}
