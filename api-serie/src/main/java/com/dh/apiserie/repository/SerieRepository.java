package com.dh.apiserie.repository;

import com.dh.apiserie.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {

    //@Query("{'genre': ?0}")
    List<Serie> findByGenre(String genre);

    @Transactional
    @Query("'id': ?0}")
    Optional<Serie> findById(String id);

}
