package com.election.parlement.service;

import com.election.parlement.dto.ProvinceDTO;
import com.election.parlement.entity.Province;
import com.election.parlement.repo.ProvinceRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional // make sequence operation
public class ProvinceServiceImpl implements ProvinceService{

    @Autowired
    private ProvinceRepo provinceRepo;

    @Override
    public Province createProvince(ProvinceDTO provinceDTO){
        Province province = new Province();
        BeanUtils.copyProperties(provinceDTO, province);
        return provinceRepo.save(province);
    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepo.findAll();
    }

    @Override
    public Province updateProvince(int id, String name, int districtCount) {
        if (provinceRepo.existsByName(name)) return null;
        return provinceRepo.findById(id).map(province -> {
            province.setName(name);
            province.setDistrictCount(districtCount);
            return provinceRepo.save(province);
        }).orElse(null);
    }

    @Override
    public boolean deleteProvince(int id) {
        if (provinceRepo.existsById(id)) {
            provinceRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public long  getProvinceCount() {
        return provinceRepo.count();
    }

    @Override
    public Optional<Province> getProvinceById(int id) {
        return provinceRepo.findById(id);
    }


}

