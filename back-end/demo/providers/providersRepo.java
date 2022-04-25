package com.example.demo.providers;

import com.example.demo.channels.channel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface providersRepo extends CrudRepository<providers,Integer> {
    providers findById(int id);

    @Transactional
    @Modifying
    @Query(value="UPDATE channel\n" +
            "SET price = price + (price*(:percent/100))\n" +
            "WHERE providers_id = :id",nativeQuery = true)
    void updatePrice(@Param("id") int id,@Param("percent") double percent);
}
