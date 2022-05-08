package com.example.demo.repositories;

import com.example.demo.entities.Clients;
import org.springframework.data.repository.CrudRepository;

public interface ClientsRepo extends CrudRepository<Clients,Integer> {
}
