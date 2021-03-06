package com.example.paginationgridview.controller;

import com.example.paginationgridview.entite.Test;
import com.example.paginationgridview.service.TestService;
import com.example.paginationgridview.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/savetest")
    public Test createVersements(@Valid @RequestBody Test test) throws URISyntaxException {
        testService.create(test);
        return test;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/tests")
    public Page<Test> getAllProducts(@RequestParam(value = "skip") int page, @RequestParam(value = "take") int limit, @RequestParam(value = "orderby", required=false) String orderby , @RequestParam(value = "desc", required=false) boolean desc, @RequestParam(value = "filter", required=false) List filter) throws URISyntaxException {
        List<SearchCriteria> formedFilter = new ArrayList<>();
        SearchCriteria sc = new SearchCriteria();
        if(filter != null){
            formedFilter = IntStream.iterate(0, i -> i + 4)
                .limit(filter.size() / 4 + 1)
                .mapToObj(i -> new SearchCriteria((String) filter.get(i), (String) filter.get(i + 1), filter.get(i + 2)))
                .collect(Collectors.toList());

        }
        return testService.findAll(page, limit,orderby, desc,formedFilter);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/comptesfiltred")
    public List getAllFiltred(@RequestBody List<SearchCriteria> searchCriteria) throws URISyntaxException {
        return testService.givenLast_whenGettingListOfUsers_thenCorrect(searchCriteria);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/savemany")
    public void tests(@Valid @RequestBody List<Test> test ) throws URISyntaxException {
        testService.createMany(test);
    }
}
