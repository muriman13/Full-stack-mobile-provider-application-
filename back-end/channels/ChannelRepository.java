package com.example.demo.channels;

import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelRepository extends CrudRepository<channel, Integer> {

    

    void deleteByname(String name);
    List<channel> findById(int id);
    List<channel> findByname(String id);

    @Query(value = "select * from channel \n" +
            "join channels_has_packages on channel.id = channels_has_packages.channels_id \n" +
            "where channels_has_packages.packages_id = :pack_id",nativeQuery = true)
    List<channel> withCertainId(@Param("pack_id") Integer pack_id);


    @Query(value = "select * from channel where type = :cat",nativeQuery = true)
    List<channel> withCat(@Param("cat") String cat);


    @Query(value =
            "select * from channel\n" +
            "join contract on channel.contract_id = :id\n" +
            "group by channel.id", nativeQuery = true)
    List<channel> getInContract(@Param("id") Integer id);


}
