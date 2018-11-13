package com.example.paginationgridview.controller;

import com.example.paginationgridview.entite.Test;
import com.example.paginationgridview.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

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
    public Page<Test> getAllProducts(@RequestParam(value = "skip") int page, @RequestParam(value = "take") int limit, @RequestParam(value = "orderby", required=false) String orderby , @RequestParam(value = "desc", required=false) boolean desc) throws URISyntaxException {
        return testService.findAll(page, limit,orderby, desc);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/testNouioui")
    public Page<Test> fullTextSearch(@RequestParam(value = "skip") int page, @RequestParam(value = "take") int limit, @RequestParam(value = "orderby", required=false) String orderby , @RequestParam(value = "desc", required=false) boolean desc) throws URISyntaxException {
        return testService.fullTextSearch(page, limit,orderby, desc);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/comptesfiltred")
    public List getAllFiltred(@RequestParam(value = "key") String key, @RequestParam(value = "operation")String operation,@RequestParam(value = "value") Object value) throws URISyntaxException {
        return testService.givenLast_whenGettingListOfUsers_thenCorrect(key, operation, value);
    }
}
