package com.example.demo.pack;

import com.example.demo.channels.channel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface packRepository extends CrudRepository<pack, Integer> {


    static void getinContratct(int id) {
    }

    void deleteByname(String name);

     List<pack> findByname(String id);
    @Modifying
    @Query("update pack u set u.name = :name where u.id = :id")
    void updatePack(@Param(value  = "name")String name,@Param(value = "id")Integer id);

    @Query(value =
            "select * from packages\n" +
                    "join contract on packages.contract_id = :id\n" +
                    "group by packages.id", nativeQuery = true)
    List<pack> getInContract(@Param("id") Integer id);
}
