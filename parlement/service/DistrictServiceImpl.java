package com.election.parlement.service;

import com.election.parlement.dto.DistrictDTO;
import com.election.parlement.entity.District;
import com.election.parlement.repo.DistrictRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepo districtRepo;

    @Override
    public District createDistrict(District district) {
        if (district.getName() == null || district.getName().isBlank()
                || district.getProvince() == null || district.getProvince().getId() <= 0 || district.getAllocatedSeats() <= 0) {
            throw new IllegalArgumentException("All fields must be properly filled");
        }

        if (districtRepo.existsByName(district.getName())) {
            throw new IllegalArgumentException("District with the same name already exists");
        }

        return districtRepo.save(district);
    }

    @Override
    public District create(District district) {
        return createDistrict(district);
    }

    @Override
    public List<District> getAll() {
        return districtRepo.findAll();
    }

    @Override
    public District getById(int id) {
        return districtRepo.findById((long) id).orElseThrow(() -> new NoSuchElementException("District not found"));
    }

    @Override
    public District update(int id, District district) {
        District existing = getById(id);

        if (districtRepo.existsByNameIgnoreCaseAndIdNot(district.getName(), (long) id)) {
            throw new IllegalArgumentException("Another district with the same name exists");
        }

        existing.setName(district.getName());
        existing.setProvince(district.getProvince());
        existing.setAllocatedSeats(district.getAllocatedSeats());

        return districtRepo.save(existing);
    }

    @Override
    public boolean delete(int id) {
        if (!districtRepo.existsById((long) id)) return false;
        districtRepo.deleteById((long) id);
        return true;
    }

    @Override
    public String getNameById(int id) {
        return getById(id).getName();
    }

    @Override
    public boolean updateName(int id, String newName) {
        if (newName == null || newName.isBlank())
            throw new IllegalArgumentException("District name must not be empty");

        if (districtRepo.existsByNameIgnoreCaseAndIdNot(newName, (long) id)) {
            throw new IllegalArgumentException("Another district with the same name already exists");
        }

        District district = getById(id);
        district.setName(newName);
        districtRepo.save(district);
        return true;
    }

    @Override
    public Integer getAllocatedSeatsById(int id) {
        return getById(id).getAllocatedSeats();
    }

    @Override
    public boolean updateAllocatedSeats(int id, int seats) {
        District district = getById(id);
        district.setAllocatedSeats(seats);
        districtRepo.save(district);
        return true;
    }

}
