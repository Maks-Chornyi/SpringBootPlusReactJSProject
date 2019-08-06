package io.agileintelligence.ppmtool.service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public Project saveOrUpdateProject(Project project) {
        return projectRepo.save(project);
    }


}
