package com.dh.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.catalog.Catalog;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog,String> {
}
