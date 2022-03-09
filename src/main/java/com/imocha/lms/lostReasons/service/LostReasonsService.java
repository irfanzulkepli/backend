package com.imocha.lms.lostReasons.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.lostReasons.entities.LostReasons;
import com.imocha.lms.lostReasons.model.LostReasonsResponse;
import com.imocha.lms.lostReasons.model.UpdateLostReasonsRequest;
import com.imocha.lms.lostReasons.repository.LostReasonsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class LostReasonsService {
    @Autowired
    private LostReasonsRepository lostReasonsRepository;

    public Page<LostReasonsResponse> page(PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<LostReasons> lostReasonsPage = lostReasonsRepository.findAll(pageRequest);

        List<LostReasonsResponse> lostReasonsResponseList = lostReasonsPage.getContent().stream().map(lostReasons -> {
            return this.mapLossReasonsResponse(lostReasons);
        }).collect(Collectors.toList());

        Page<LostReasonsResponse> lostReasonsResponsePageImpl = new PageImpl<>(lostReasonsResponseList, pageRequest,
                lostReasonsPage.getTotalElements());
        return lostReasonsResponsePageImpl;
    }

    private LostReasonsResponse mapLossReasonsResponse(LostReasons lostReasons) {
        LostReasonsResponse lostReasonsResponse = new LostReasonsResponse();
        BeanUtils.copyProperties(lostReasons, lostReasonsResponse);

        OwnerResponse ownerResponse = new OwnerResponse();
        BeanUtils.copyProperties(lostReasons.getUsers(), ownerResponse);
        lostReasonsResponse.setUsers(ownerResponse);

        return lostReasonsResponse;
    }

    public LostReasons get(long id) {
        Optional<LostReasons> lostReasonsOpt = lostReasonsRepository.findById(id);
        if (!lostReasonsOpt.isPresent()) {
            // TODO: throw error
        }

        return lostReasonsOpt.get();
    }

    public LostReasonsResponse getLossReasonsResponse(long id) {
        LostReasons lostReasons = this.get(id);
        return this.mapLossReasonsResponse(lostReasons);
    }

    public long update(UpdateLostReasonsRequest request) {
        LostReasons lostReasons = this.get(request.getId());
        lostReasons.setLostReason(request.getLostReason());
        lostReasonsRepository.save(lostReasons);
        return lostReasons.getId();
    }

    public long delete(long id) {
        this.get(id);
        lostReasonsRepository.deleteById(id);
        return id;
    }
}
