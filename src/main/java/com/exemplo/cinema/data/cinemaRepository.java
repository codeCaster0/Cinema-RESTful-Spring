package com.exemplo.cinema.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cinemaRepository extends JpaRepository<cinemaEntity, Integer> {

}
