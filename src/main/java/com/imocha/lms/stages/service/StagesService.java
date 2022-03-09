package com.imocha.lms.stages.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imocha.lms.pipelines.entities.Pipelines;
import com.imocha.lms.stages.entities.Stages;
import com.imocha.lms.stages.model.StagesListResponse;
import com.imocha.lms.stages.repository.StagesRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StagesService {
    @Autowired
    private StagesRepository stagesRepository;

    public List<StagesListResponse> list() {
        List<Stages> list = stagesRepository.findAll();
        List<StagesListResponse> response = new ArrayList<StagesListResponse>();
        for (Stages pipelines : list) {
            StagesListResponse pipelinesListResponse = this.mapStagesToStagesListResponse(pipelines);
            response.add(pipelinesListResponse);
        }
        return response;
    }

    private StagesListResponse mapStagesToStagesListResponse(Stages stages) {
        StagesListResponse response = new StagesListResponse();
        BeanUtils.copyProperties(stages, response);
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
