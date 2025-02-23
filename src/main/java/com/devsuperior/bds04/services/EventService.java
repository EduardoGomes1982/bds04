package com.devsuperior.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    public Page<EventDTO> findAllPaged(Pageable pageable) {
        Page<Event> page = repository.findAll(pageable);
        return page.map(e -> new EventDTO(e));
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event entity = new Event(null, dto.getName(), dto.getDate(), dto.getUrl(), new City(dto.getCityId(), ""));
        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}
