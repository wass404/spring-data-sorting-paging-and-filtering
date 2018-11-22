package com.example.paginationgridview.service;

import com.example.paginationgridview.Repositories.CompteRepository;
import com.example.paginationgridview.Repositories.Compte_TestRepository;
import com.example.paginationgridview.entite.Compte;
import com.example.paginationgridview.entite.Compte_Test;
import com.example.paginationgridview.entite.Test;
import com.example.paginationgridview.specification.SearchCriteria;
import com.example.paginationgridview.specification.TestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

//    @Autowired
//    private Compte_TestRepository compte_testRepository;
    public void create(Compte compte) {

        compteRepository.save(compte);
    }

//    @Transactional(readOnly = true)
//    public List findAll() {
//
//       return compteRepository.fullTextSearch();
//    }


    public Page fullTextSearch(int page, int limit, String sortBy, boolean desc) {
        Pageable pageableRequest;
        Page users = null;
        if (sortBy == null) {
            pageableRequest = PageRequest.of(page, limit);
            users = compteRepository.fullTextSearch(pageableRequest);

        } else {
            if (desc) {

                try{
                    pageableRequest = PageRequest.of(page, limit, new Sort(
                            new Sort.Order(Sort.Direction.DESC, sortBy)
                    ));
                    users = compteRepository.fullTextSearch(pageableRequest);
                }catch (Exception e){
                    pageableRequest = PageRequest.of(page, limit, new Sort(
                            new Sort.Order(Sort.Direction.DESC, "test." + sortBy)
                    ));
                    users = compteRepository.fullTextSearch(pageableRequest);
                }
            } else {
                try{
                    pageableRequest = PageRequest.of(page, limit, new Sort(
                            new Sort.Order(Sort.Direction.ASC, sortBy)
                    ));
                    users = compteRepository.fullTextSearch(pageableRequest);
                }catch (Exception e){
                    pageableRequest = PageRequest.of(page, limit, new Sort(
                            new Sort.Order(Sort.Direction.ASC, "test." + sortBy)
                    ));
                    users = compteRepository.fullTextSearch(pageableRequest);
                }
            }

        }
        return users;
    }





}
