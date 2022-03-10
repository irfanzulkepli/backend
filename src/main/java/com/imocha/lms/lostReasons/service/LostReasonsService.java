package com.imocha.lms.lostReasons.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.lostReasons.entities.LostReasons;
import com.imocha.lms.lostReasons.model.AddLostReasonsRequest;
import com.imocha.lms.lostReasons.model.LostReasonsPageResponse;
import com.imocha.lms.lostReasons.model.LostReasonsResponse;
import com.imocha.lms.lostReasons.model.UpdateLostReasonsRequest;
import com.imocha.lms.lostReasons.repository.LostReasonsRepository;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

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

    @Autowired
    private UsersService usersService;

    public Page<LostReasonsPageResponse> page(PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<LostReasons> lostReasonsPage = lostReasonsRepository.findAll(pageRequest);

        List<LostReasonsPageResponse> lostReasonsResponseList = lostReasonsPage.getContent().stream()
                .map(lostReasons -> {
                    LostReasonsPageResponse pageResponse = new LostReasonsPageResponse();
                    BeanUtils.copyProperties(lostReasons, pageResponse);

                    if (lostReasons.isActive()) {
                        pageResponse.setActive("ACTIVE");
                    } else {
                        pageResponse.setActive("INACTIVE");
                    }

                    OwnerResponse ownerResponse = new OwnerResponse();
                    BeanUtils.copyProperties(lostReasons.getUsers(), ownerResponse);
                    pageResponse.setCreatedBy(ownerResponse);

                    return pageResponse;
                }).collect(Collectors.toList());

        Page<LostReasonsPageResponse> lostReasonsResponsePageImpl = new PageImpl<>(lostReasonsResponseList, pageRequest,
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

    public long add(AddLostReasonsRequest request) {
        Users users = usersService.get(1);

        LostReasons lostReasons = new LostReasons();
        lostReasons.setLostReason(request.getLostReason());
        lostReasons.setUsers(users);
        lostReasonsRepository.save(lostReasons);
        return lostReasons.getId();
    }

    public long update(long id, UpdateLostReasonsRequest request) {
        LostReasons lostReasons = this.get(id);
        lostReasons.setLostReason(request.getLostReason());
        lostReasonsRepository.save(lostReasons);
        return lostReasons.getId();
    }

    public long delete(long id) {
        LostReasons lostReasons = this.get(id);
        lostReasons.setActive(false);
        LostReasons savedLostReasons = lostReasonsRepository.save(lostReasons);
        return savedLostReasons.getId();
    }
}
