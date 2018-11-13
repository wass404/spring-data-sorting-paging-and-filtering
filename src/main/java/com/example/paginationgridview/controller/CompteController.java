package com.example.paginationgridview.controller;

import com.example.paginationgridview.entite.Compte;
import com.example.paginationgridview.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompteController {

    @Autowired
    private CompteService compteService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/savecompte")
    public Compte createVersements(@Valid @RequestBody Compte compte) throws URISyntaxException {
        compteService.create(compte);
        return compte;
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/comptes")
    public Page getAllProducts(@RequestParam(value = "skip") int page, @RequestParam(value = "take") int limit, @RequestParam(value = "orderby", required=false) String orderby , @RequestParam(value = "desc", required=false) boolean desc) throws URISyntaxException {
        return compteService.fullTextSearch(page, limit,orderby, desc);
    }



}
