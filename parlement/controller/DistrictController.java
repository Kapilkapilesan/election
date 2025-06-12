package com.election.parlement.controller;

import com.election.parlement.dto.DistrictDTO;
import com.election.parlement.entity.District;
import com.election.parlement.entity.Province;
import com.election.parlement.service.DistrictService;
import com.election.parlement.service.ProvinceService;
import com.election.parlement.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private ProvinceService provinceService;

    @PostMapping
    public ResponseEntity<StandardResponse> createDistrict(@RequestBody DistrictDTO districtDTO) {

        Province province = provinceService.getProvinceById(districtDTO.getProvinceId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Province not found"));
        District district = new District(0, districtDTO.getName(), province, districtDTO.getAllocatedSeats());
        District createdDistrict = districtService.createDistrict(district);
        return ResponseEntity.status(201).body(
                new StandardResponse("Success", "District created successfully", createdDistrict, 201)
        );
    }

    @GetMapping
    public List<District> getAllDistricts() {
        return districtService.getAll();
    }

    @GetMapping("/{id}")
    public District getDistrictById(@PathVariable int id) {
        return districtService.getById(id);
     }

    @PutMapping("/{id}")
    public District updateDistrict(@PathVariable int id, @RequestBody DistrictDTO dto) {
        Province province = provinceService.getProvinceById(dto.getProvinceId().intValue())
                .orElseThrow(() -> new IllegalArgumentException("Province not found"));
        return districtService.update(id, new District(id, dto.getName(), province, dto.getAllocatedSeats()));
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteDistrict(@PathVariable int id) {
        boolean deleted = districtService.delete(id);
        return Map.of("message", deleted ? "District deleted" : "District not found");
    }

    @GetMapping("/name/{id}")
    public Map<String, String> getDistrictName(@PathVariable int id) {
        return Map.of("name", districtService.getNameById(id));
    }

    @PutMapping("/name/{id}")
    public Map<String, String> updateDistrictName(@PathVariable int id, @RequestBody Map<String, String> body) {
        districtService.updateName(id, body.get("name"));
        return Map.of("message", "District name updated");
    }

    @GetMapping("/seats/{id}")
    public Map<String, Integer> getSeats(@PathVariable int id) {
        return Map.of("allocatedSeats", districtService.getAllocatedSeatsById(id));
    }

    @PutMapping("/seats/{id}")
    public Map<String, String> updateSeats(@PathVariable int id, @RequestBody Map<String, Integer> body) {
        districtService.updateAllocatedSeats(id, body.get("allocatedSeats"));
        return Map.of("message", "Allocated seats updated");
    }


}
