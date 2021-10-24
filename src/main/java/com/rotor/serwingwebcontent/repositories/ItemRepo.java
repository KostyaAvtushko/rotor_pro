package com.rotor.serwingwebcontent.repositories;


import com.rotor.serwingwebcontent.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Integer> {
}
