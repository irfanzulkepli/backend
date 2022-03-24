package com.imocha.lms.deals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.helper.UserHelper;
import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.entities.LostReasons;
import com.imocha.lms.deals.model.AddLostReasonsRequest;
import com.imocha.lms.deals.model.LostReasonsListResponse;
import com.imocha.lms.deals.model.LostReasonsPageResponse;
import com.imocha.lms.deals.model.LostReasonsResponse;
import com.imocha.lms.deals.model.UpdateLostReasonsRequest;
import com.imocha.lms.deals.repositories.LostReasonsRepository;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.users.entities.Users;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LostReasonsService {
    @Autowired
    private LostReasonsRepository lostReasonsRepository;

	@Autowired
	UserHelper userHelper;

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

    public List<LostReasonsListResponse> list() {
        List<LostReasons> list = lostReasonsRepository.findAll();
        List<LostReasonsListResponse> listResponse = new ArrayList<LostReasonsListResponse>();
        for (LostReasons lostReasons : list) {
            LostReasonsListResponse response = new LostReasonsListResponse();
            BeanUtils.copyProperties(lostReasons, response);
            listResponse.add(response);
        }
        return listResponse;
    }

    public LostReasons get(long id) {
        Optional<LostReasons> lostReasonsOpt = lostReasonsRepository.findById(id);
        lostReasonsOpt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        return lostReasonsOpt.get();
    }

    public LostReasonsResponse getLossReasonsResponse(long id) {
        LostReasons lostReasons = this.get(id);
        LostReasonsResponse lostReasonsResponse = new LostReasonsResponse();
        BeanUtils.copyProperties(lostReasons, lostReasonsResponse);

        OwnerResponse ownerResponse = new OwnerResponse();
        BeanUtils.copyProperties(lostReasons.getUsers(), ownerResponse);
        lostReasonsResponse.setUsers(ownerResponse);

        return lostReasonsResponse;
    }

    public long add(AddLostReasonsRequest request) {
        Users users = userHelper.getCurrentLoginUser();

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
