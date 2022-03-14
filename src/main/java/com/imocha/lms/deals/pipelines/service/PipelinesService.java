package com.imocha.lms.deals.pipelines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.model.PipelinesListResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesPageResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesResponse;
import com.imocha.lms.deals.pipelines.repository.PipelinesRepository;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.leads.model.OwnerResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PipelinesService {
    @Autowired
    private PipelinesRepository pipelinesRepository;

    @Lazy
    @Autowired
    private DealsService dealsService;

    @Lazy
    @Autowired
    private StagesService stagesService;

    public Page<PipelinesPageResponse> page(@Valid PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<Pipelines> pipelinesPage = pipelinesRepository.findAll(pageRequest);

        List<PipelinesPageResponse> pipelinesResponseList = pipelinesPage.getContent().stream().map(pipelines -> {

            PipelinesPageResponse pipelinesResponse = new PipelinesPageResponse();
            BeanUtils.copyProperties(pipelines, pipelinesResponse);

            if (pipelines.isActive()) {
                pipelinesResponse.setActive("ACTIVE");
            } else {
                pipelinesResponse.setActive("INACTIVE");
            }

            OwnerResponse ownerResponse = new OwnerResponse();
            BeanUtils.copyProperties(pipelines.getUsers(), ownerResponse);
            pipelinesResponse.setUsers(ownerResponse);

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
        List<Pipelines> list = pipelinesRepository.findAll();
        List<PipelinesListResponse> response = new ArrayList<PipelinesListResponse>();
        for (Pipelines pipelines : list) {
            PipelinesListResponse pipelinesListResponse = this.mapPipelinesToPipelinesListResponse(pipelines);
            response.add(pipelinesListResponse);
        }
        return response;
    }

    public Pipelines get(long id) {
        Optional<Pipelines> pOptional = pipelinesRepository.findById(id);
        if (!pOptional.isPresent()) {
            // TODO: throw error;
        }

        return pOptional.get();
    }

    public Pipelines getFirstPipelines() {
        List<Pipelines> list = pipelinesRepository.findAll();
        if (list.size() <= 0) {
            // TODO: throw error;
        }

        return list.get(0);
    }

    public long delete(long id) {
        Pipelines pipelines = this.get(id);
        pipelines.setActive(false);
        Pipelines savedPipelines = pipelinesRepository.save(pipelines);
        return savedPipelines.getId();
    }
}
