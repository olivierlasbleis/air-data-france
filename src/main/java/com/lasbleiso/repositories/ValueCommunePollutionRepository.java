package com.lasbleiso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lasbleiso.entities.ValueCommunePollution;

@Repository
public interface ValueCommunePollutionRepository extends JpaRepository<ValueCommunePollution,Integer>{

}
