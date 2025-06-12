package com.election.parlement.service;

import com.election.parlement.dto.DistrictDTO;
import com.election.parlement.entity.District;

import java.util.List;

public interface DistrictService {
    District createDistrict(District district);
    District create(District district);
    List<District> getAll();
    District getById(int id);
    District update(int id, District district);
    boolean delete(int id);
    String getNameById(int id);
    boolean updateName(int id, String newName);
    Integer getAllocatedSeatsById(int id);
    boolean updateAllocatedSeats(int id, int seats);
}
