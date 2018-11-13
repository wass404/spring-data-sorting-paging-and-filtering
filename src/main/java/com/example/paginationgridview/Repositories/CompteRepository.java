package com.example.paginationgridview.Repositories;

import com.example.paginationgridview.entite.Compte;
import com.example.paginationgridview.entite.Compte_Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long>, JpaSpecificationExecutor<Compte> {
    @Query(value = "SELECT c2.id as idTest,c1.id as idCompte,c1.solde as solde,c1.numero as numero, c2.nom as nom, c2.prenom as prenom FROM Compte c1, Test c2\n" +
            "WHERE c1.test.id = c2.id ")
    Page<Map>fullTextSearch(Pageable pageable);
}
