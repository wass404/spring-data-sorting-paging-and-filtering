package com.example.paginationgridview.Repositories;

import com.example.paginationgridview.entite.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> , JpaSpecificationExecutor<Test> {
    @Query(value = "select t from Test t where t.nom = 'Nouioui' ")
    Page<Test> fullTextSearch(Pageable pageable);
}
