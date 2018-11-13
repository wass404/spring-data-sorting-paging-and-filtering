package com.example.paginationgridview.service;

import com.example.paginationgridview.Repositories.TestRepository;
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

import java.util.Date;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public void create(Test test) {

        testRepository.save(test);
    }

    @Transactional(readOnly = true)
    public Page<Test> findAll(int page, int limit, String sortBy, boolean desc) {
        Pageable pageableRequest;
        if (sortBy == null) {
            pageableRequest = PageRequest.of(page, limit);

        } else {
            if (desc) {
                pageableRequest = PageRequest.of(page, limit, new Sort(
                        new Sort.Order(Sort.Direction.DESC, sortBy)
                ));
            } else {
                pageableRequest = PageRequest.of(page, limit, new Sort(
                        new Sort.Order(Sort.Direction.ASC, sortBy)
                ));
            }

        }
        Page<Test> users = testRepository.findAll(pageableRequest);
        return users;
    }


    public Page<Test> fullTextSearch(int page, int limit, String sortBy, boolean desc) {
        Pageable pageableRequest;
        if (sortBy == null) {
            pageableRequest = PageRequest.of(page, limit);

        } else {
            if (desc) {
                pageableRequest = PageRequest.of(page, limit, new Sort(
                        new Sort.Order(Sort.Direction.DESC, sortBy)
                ));
            } else {
                pageableRequest = PageRequest.of(page, limit, new Sort(
                        new Sort.Order(Sort.Direction.ASC, sortBy)
                ));
            }

        }
        Page<Test> users = testRepository.fullTextSearch(pageableRequest);
        return users;
    }



    public List givenLast_whenGettingListOfUsers_thenCorrect(String key, String operation, Object value) {
        TestSpecification spec =
                new TestSpecification(new SearchCriteria(key, operation, value));

        List<Test> results = testRepository.findAll((Specification)spec);
        return results;

    }

}
