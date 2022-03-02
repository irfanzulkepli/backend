package com.imocha.lms.deals.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.repositories.DealsRepository;
import com.imocha.lms.entities.Statuses;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;

    public List<Statuses> getPersonDealsCountByStatus(Long id) {
        List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person",
                id);

        deals.stream().filter(deal -> deal.getStatuses().equals("status_open")).count();

        return null;
    }
}
