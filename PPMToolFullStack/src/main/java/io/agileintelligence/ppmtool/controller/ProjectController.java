package io.agileintelligence.ppmtool.controller;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.service.MapValidationErrorService;
import io.agileintelligence.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
        if (errorMap != null) return errorMap;

        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
