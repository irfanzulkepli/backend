package com.imocha.lms.deals.pipelines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imocha.common.helper.UserHelper;
import com.imocha.lms.deals.pipelines.entities.DefaultStages;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;
import com.imocha.lms.deals.pipelines.model.DefaultStagesResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesResponse;
import com.imocha.lms.deals.pipelines.model.StagesListResponse;
import com.imocha.lms.deals.pipelines.model.UpdateStageRequest;
import com.imocha.lms.deals.pipelines.repository.DefaultStagesRepository;
import com.imocha.lms.deals.pipelines.repository.StagesRepository;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.users.entities.Users;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StagesService {
    @Autowired
    private StagesRepository stagesRepository;

    @Autowired
    private DefaultStagesRepository defaultStagesRepository;

    @Autowired
    private PipelinesService pipelinesService;

	@Autowired
	UserHelper userHelper;

    @Lazy
    @Autowired
    private DealsService dealsService;

    public List<StagesListResponse> listByPipelinesId(String id) {
        long pipelinesId = 0;
        if (StringUtils.isBlank(id)) {
            pipelinesId = pipelinesService.getFirstPipelines().getId();
        } else {
            pipelinesId = Integer.parseInt(id);
        }

        Pipelines pipelines = pipelinesService.get(pipelinesId);
        List<Stages> list = stagesRepository.findByPipelines(pipelines);
        List<StagesListResponse> response = new ArrayList<StagesListResponse>();
        for (Stages stages : list) {
            if (stages.isActive()) {
                StagesListResponse pipelinesListResponse = this.mapStagesToStagesListResponse(stages);
                response.add(pipelinesListResponse);
            }
        }
        return response;
    }

    private StagesListResponse mapStagesToStagesListResponse(Stages stages) {
        StagesListResponse response = new StagesListResponse();
        BeanUtils.copyProperties(stages, response);

        int dealsCount = dealsService.checkDealsExistByStages(stages);
        response.setDealsCount(dealsCount);

        PipelinesResponse pipelinesResponse = new PipelinesResponse();
        BeanUtils.copyProperties(stages.getPipelines(), pipelinesResponse);
        response.setPipelines(pipelinesResponse);
        return response;
    }

    public Stages get(long id) {
        Optional<Stages> sOptional = stagesRepository.findById(id);
        sOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

        return sOptional.get();
    }

    public int getStagesCountByPipelines(Pipelines pipelines) {
        List<Stages> stagesList = stagesRepository.findByPipelines(pipelines);
        return stagesList.size();
    }

    public List<DefaultStagesResponse> defaultList() {
        List<DefaultStages> list = defaultStagesRepository.findAll();
        List<DefaultStagesResponse> responseList = new ArrayList<DefaultStagesResponse>();

        for (DefaultStages defaultStages : list) {
            DefaultStagesResponse response = new DefaultStagesResponse();
            BeanUtils.copyProperties(defaultStages, response);
            responseList.add(response);
        }
        return responseList;
    }

    public long createOrUpdateStage(Pipelines pipelines, UpdateStageRequest request) {
        Stages stages = new Stages();

        if (request.getId() > 0) {

            Optional<Stages> sOptional = stagesRepository.findByPipelinesAndId(pipelines, request.getId());
            if (sOptional.isPresent()) {
                stages = sOptional.get();
            }
        }

        stages.setName(request.getName());
        stages.setPriority(request.getPriority());
        stages.setProbability(request.getProbability());
        stages.setPipelines(pipelines);
        Stages savedStages = stagesRepository.save(stages);
        return savedStages.getId();
    }

    public long delete(long id) {
        Stages stages = this.get(id);
        stages.setActive(false);
        Stages savedStages = stagesRepository.save(stages);
        return savedStages.getId();
    }
}
