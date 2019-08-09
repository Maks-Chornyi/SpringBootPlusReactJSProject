package io.agileintelligence.ppmtool.service;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exception.ProjectIDException;
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
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepo.save(project);
        } catch (Exception e) {
            throw new ProjectIDException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByProjectIdentifier(String projectIdentifier) {
        Project project =  projectRepo.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
        if (project == null) {
            throw new ProjectIDException("Project with ID '" + projectIdentifier + "' does not exist");
        }
        return project;
    }


}
