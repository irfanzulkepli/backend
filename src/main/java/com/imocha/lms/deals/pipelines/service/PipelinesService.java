package com.imocha.lms.deals.pipelines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.model.AddPipelinesRequest;
import com.imocha.lms.deals.pipelines.model.PipelinesListResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesPageResponse;
import com.imocha.lms.deals.pipelines.model.UpdatePipelinesRequest;
import com.imocha.lms.deals.pipelines.model.UpdateStageRequest;
import com.imocha.lms.deals.pipelines.repository.PipelinesRepository;
import com.imocha.lms.deals.pipelines.specification.PipelinesSpecification;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PipelinesService {
    @Autowired
    private PipelinesRepository pipelinesRepository;

    @Autowired
    private PipelinesSpecification pipelinesSpecification;

    @Lazy
    @Autowired
    private DealsService dealsService;

    @Lazy
    @Autowired
    private StagesService stagesService;

    @Autowired
    UsersService usersService;

    public Page<PipelinesPageResponse> page(@Valid PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Specification<Pipelines> spec = pipelinesSpecification.getSpecification(pageableRequest);
		Page<Pipelines> pipelinesPage = pipelinesRepository.findAll(spec, pageRequest);

        List<PipelinesPageResponse> pipelinesResponseList = pipelinesPage.getContent().stream().map(pipelines -> {

            PipelinesPageResponse pipelinesResponse = new PipelinesPageResponse();
            BeanUtils.copyProperties(pipelines, pipelinesResponse);

            OwnerResponse createdByResponse = new OwnerResponse();
            Users createdBy = usersService.getByKeycloakId(pipelines.getCreatedBy());
            BeanUtils.copyProperties(createdBy, createdByResponse);
            pipelinesResponse.setUsers(createdByResponse);

            long totalDealValue = dealsService.getTotalDealValueByPipelines(pipelines);
            pipelinesResponse.setTotalDealValue(totalDealValue);

            int dealsCount = dealsService.getDealsCountByPipelines(pipelines);
            pipelinesResponse.setDealsCount(dealsCount);

            int stagesCount = stagesService.getStagesCountByPipelines(pipelines);
            pipelinesResponse.setStagesCount(stagesCount);

            return pipelinesResponse;
        }).collect(Collectors.toList());

        Page<PipelinesPageResponse> pipelinesResponsePageImpl = new PageImpl<>(pipelinesResponseList, pageRequest,
                pipelinesPage.getTotalElements());
        return pipelinesResponsePageImpl;
    }

    private PipelinesListResponse mapPipelinesToPipelinesListResponse(Pipelines pipelines) {
        PipelinesListResponse response = new PipelinesListResponse();
        BeanUtils.copyProperties(pipelines, response);
        return response;
    }

    public List<PipelinesListResponse> list() {
        List<Pipelines> list = pipelinesRepository.findByActiveTrue();
        List<PipelinesListResponse> response = new ArrayList<PipelinesListResponse>();
        for (Pipelines pipelines : list) {
            PipelinesListResponse pipelinesListResponse = this.mapPipelinesToPipelinesListResponse(pipelines);
            response.add(pipelinesListResponse);
        }
        return response;
    }

    public Pipelines get(long id) {
        Optional<Pipelines> pOptional = pipelinesRepository.findById(id);
        pOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        return pOptional.get();
    }

    public Pipelines getFirstPipelines() {
        List<Pipelines> list = pipelinesRepository.findByActiveTrue();
        if (list.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

        return list.get(0);
    }

    public long add(AddPipelinesRequest request) {
        Pipelines pipelines = new Pipelines();
        pipelines.setName(request.getName());

        Pipelines savedPipelines = pipelinesRepository.save(pipelines);

        for (UpdateStageRequest stagesRequest : request.getStages()) {
            stagesService.createOrUpdateStage(pipelines, stagesRequest);
        }

        return savedPipelines.getId();
    }

    public long update(UpdatePipelinesRequest request) {
        Pipelines pipelines = this.get(request.getId());
        pipelines.setName(request.getName());
        Pipelines savedPipelines = pipelinesRepository.save(pipelines);

        for (UpdateStageRequest stagesRequest : request.getStages()) {
            stagesService.createOrUpdateStage(pipelines, stagesRequest);
        }

        return savedPipelines.getId();
    }

    public long delete(long id) {
        Pipelines pipelines = this.get(id);
        pipelines.setActive(false);
        Pipelines savedPipelines = pipelinesRepository.save(pipelines);
        return savedPipelines.getId();
    }
}
