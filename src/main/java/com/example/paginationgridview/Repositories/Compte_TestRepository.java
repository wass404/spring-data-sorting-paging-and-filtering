package com.example.paginationgridview.Repositories;

import com.example.paginationgridview.entite.Compte_Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface Compte_TestRepository {
//    @Query(value = "SELECT new com.example.paginationgridview.entite.Compte_Test(c2.id,c1.id,) FROM Compte c1 INNER JOIN Test c2 ON c1.id_test= c2.id")
//    List<Compte_Test> fullTextSearch(Pageable pageable);
}
