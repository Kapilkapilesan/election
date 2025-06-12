package com.election.parlement.controller;

import com.election.parlement.dto.ProvinceDTO;
import com.election.parlement.entity.Province;
import com.election.parlement.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
@CrossOrigin
public class ProvinceController {

    @Autowired
    private ProvinceService service;

    @GetMapping
    public List<Province> getAll() {
        return service.getAllProvinces();
    }

    @GetMapping("/count")
    public ResponseEntity<?> getCount() {
        return ResponseEntity.ok().body(service.getProvinceCount());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return service.getProvinceById(id)
                .map(data -> ResponseEntity.ok(data))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProvinceDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty())
            return ResponseEntity.badRequest().body("Name is required");

        Province created = service.createProvince(dto);
        if (created == null)
            return ResponseEntity.status(409).body("Province name already exists");

        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ProvinceDTO dto) {
        Province updated = service.updateProvince(id, dto.getName().trim(), dto.getDistrictCount());
        if (updated == null)
            return ResponseEntity.status(409).body("Province name already exists or not found");

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        if (service.deleteProvince(id))
            return ResponseEntity.ok().body("Deleted");
        return ResponseEntity.status(404).body("Province not found");
    }
}

