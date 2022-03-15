package com.imocha.lms.deals.pipelines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;
import com.imocha.lms.deals.pipelines.model.PipelinesResponse;
import com.imocha.lms.deals.pipelines.model.StagesListResponse;
import com.imocha.lms.deals.pipelines.repository.StagesRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StagesService {
    @Autowired
    private StagesRepository stagesRepository;

    @Autowired
    private PipelinesService pipelinesService;

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
            StagesListResponse pipelinesListResponse = this.mapStagesToStagesListResponse(stages);
            response.add(pipelinesListResponse);
        }
        return response;
    }

    private StagesListResponse mapStagesToStagesListResponse(Stages stages) {
        StagesListResponse response = new StagesListResponse();
        BeanUtils.copyProperties(stages, response);

        PipelinesResponse pipelinesResponse = new PipelinesResponse();
        BeanUtils.copyProperties(stages.getPipelines(), pipelinesResponse);
        response.setPipelines(pipelinesResponse);
        return response;
    }

    public Stages get(long id) {
        Optional<Stages> sOptional = stagesRepository.findById(id);
        if (!sOptional.isPresent()) {
            // TODO: throw error
        }

        return sOptional.get();
    }

    public int getStagesCountByPipelines(Pipelines pipelines) {
        List<Stages> stagesList = stagesRepository.findByPipelines(pipelines);
        return stagesList.size();
    }
}
