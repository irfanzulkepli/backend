package com.imocha.lms.pipelines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.pipelines.entities.Pipelines;
import com.imocha.lms.pipelines.model.PipelinesListResponse;
import com.imocha.lms.pipelines.model.PipelinesResponse;
import com.imocha.lms.pipelines.repository.PipelinesRepository;
import com.imocha.lms.stages.service.StagesService;

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

    public Page<PipelinesResponse> page(@Valid PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<Pipelines> pipelinesPage = pipelinesRepository.findAll(pageRequest);

        List<PipelinesResponse> pipelinesResponseList = pipelinesPage.getContent().stream().map(pipelines -> {

            PipelinesResponse pipelinesResponse = new PipelinesResponse();
            BeanUtils.copyProperties(pipelines, pipelinesResponse);

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

        Page<PipelinesResponse> pipelinesResponsePageImpl = new PageImpl<>(pipelinesResponseList, pageRequest,
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

    public long delete(long id) {
        this.get(id);
        pipelinesRepository.deleteById(id);
        return id;
    }
}
