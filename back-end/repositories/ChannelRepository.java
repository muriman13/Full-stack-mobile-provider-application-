package com.example.demo.repositories;

import com.example.demo.entities.Channel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends CrudRepository<Channel, Integer> {

    


    void deleteByname(String name);
    Optional<Channel> findById(int id);
    Optional<List<Channel>> findByname(String id);

    @Query(value = "select * from channel \n" +
            "join channels_has_packages on channel.id = channels_has_packages.channels_id \n" +
            "where channels_has_packages.packages_id = :pack_id",nativeQuery = true)
    Optional<List<Channel>> withCertainId(@Param("pack_id") Integer pack_id);


    @Query(value = "select * from channel where type = :category",nativeQuery = true)
    Optional<List<Channel>> withCat(@Param("category") String category);


    @Query(value =
            "select * from channel\n" +
            "join contract on channel.contract_id = :id\n" +
            "group by channel.id", nativeQuery = true)
    Optional< List<Channel>> getInContract(@Param("id") Integer id);


}
