package com.devsuperior.bds04.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;
    
    @Transactional(readOnly = true)
    public List<CityDTO> findAllSortedByName() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(e -> new CityDTO(e)).toList();
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City(null, dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
    }
}
