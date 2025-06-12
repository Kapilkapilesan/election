package com.election.parlement.service;

import com.election.parlement.dto.ProvinceDTO;
import com.election.parlement.entity.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {
    Province createProvince(ProvinceDTO provinceDTO);
    List<Province> getAllProvinces();
    Optional<Province> getProvinceById(int id);
    Province updateProvince(int id, String name, int districtCount);
    boolean deleteProvince(int id);
    long  getProvinceCount();


   ;
}

