package com.election.parlement.controller;



import com.election.parlement.dto.PartyDTO;
import com.election.parlement.entity.Party;
import com.election.parlement.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parties")
@CrossOrigin(origins = "*")
public class PartyController {

    @Autowired
    private PartyService service;

    @GetMapping
    public List<Party> getAllParties() {
        return service.getAllParties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPartyById(@PathVariable int id) {
        Party party = service.getPartyById(id);
        return party != null ? ResponseEntity.ok(party) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createParty(
            @RequestParam("name") String name,
            @RequestParam("color") String color,
            @RequestParam(value = "districts", required = false) List<Integer> districts,
            @RequestParam(value = "logo", required = false) MultipartFile logoFile) {
        String logoPath = null;
        if (logoFile != null && !logoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(logoFile.getOriginalFilename());
            File dest = new File("uploads/" + fileName);
            dest.getParentFile().mkdirs();
            try {
                logoFile.transferTo(dest);
                logoPath = "http://localhost:8080/uploads/" + fileName;
            } catch (IOException e) {
                return ResponseEntity.status(500).body(Map.of("error", "Failed to upload logo"));
            }
        }
        PartyDTO dto = new PartyDTO();
        dto.setName(name);
        dto.setColor(color);
        dto.setLogo(logoPath);
        dto.setDistricts(districts);
        return ResponseEntity.status(201).body(service.createParty(dto));
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<?> createPartyJson(@RequestBody PartyDTO dto) {
        if (dto.getName() == null || dto.getColor() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Name and Color are required"));
        }
        return ResponseEntity.status(201).body(service.createParty(dto));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateParty(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("color") String color,
            @RequestParam(value = "districts", required = false) List<Integer> districts,
            @RequestParam(value = "logo", required = false) MultipartFile logoFile) {
        String logoPath = null;
        if (logoFile != null && !logoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(logoFile.getOriginalFilename());
            File dest = new File("uploads/" + fileName);
            dest.getParentFile().mkdirs();
            try {
                logoFile.transferTo(dest);
                logoPath = "http://localhost:8080/uploads/" + fileName;
            } catch (IOException e) {
                return ResponseEntity.status(500).body(Map.of("error", "Failed to upload logo"));
            }
        }
        PartyDTO dto = new PartyDTO();
        dto.setName(name);
        dto.setColor(color);
        dto.setLogo(logoPath);
        dto.setDistricts(districts);
        Party updated = service.updateParty(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParty(@PathVariable int id) {
        boolean deleted = service.deleteParty(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Party deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/colors")
    public List<Party> getAllPartyColors() {
        return service.getAllPartyColors();
    }

    @GetMapping("/color/{id}")
    public ResponseEntity<?> getPartyColorById(@PathVariable int id) {
        String color = service.getPartyColorById(id);
        return color != null ? ResponseEntity.ok(Map.of("id", id, "color", color))
                : ResponseEntity.status(404).body(Map.of("error", "Party or color not found"));
    }

    @PostMapping("/color")
    public ResponseEntity<?> createPartyColor(@RequestBody Map<String, String> body) {
        String color = body.get("color");
        if (color == null || color.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Color is required"));
        }
        return ResponseEntity.status(201).body(service.createPartyColor(color));
    }

    @PutMapping("/color/{id}")
    public ResponseEntity<?> updatePartyColor(@PathVariable int id, @RequestBody Map<String, String> body) {
        String color = body.get("color");
        if (color == null || color.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Color is required"));
        }
        return service.updatePartyColor(id, color)
                ? ResponseEntity.ok(Map.of("id", id, "color", color))
                : ResponseEntity.status(404).body(Map.of("error", "Party not found"));
    }

    @DeleteMapping("/color/{id}")
    public ResponseEntity<?> deletePartyColor(@PathVariable int id) {
        return service.deletePartyColor(id)
                ? ResponseEntity.ok(Map.of("message", "Party color deleted (set to null)"))
                : ResponseEntity.status(404).body(Map.of("error", "Party not found"));
    }
}
