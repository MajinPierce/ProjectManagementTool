package com.pierce.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pierce.domain.Project;
import com.pierce.services.MapValidationErrorService;
import com.pierce.services.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
@Tag(name = "Project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	
	@PostMapping("")
	@Operation(summary = "Create a new project")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project newProject, BindingResult result, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) { return errorMap; }

        Project project = projectService.saveOrUpdateProject(newProject, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
	
	
	@GetMapping("/{projectId}")
	@Operation(summary = "Get a specific project", responses = {
            @ApiResponse(description = "Get project success", responseCode = "200",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Project.class))),
            @ApiResponse(description = "Project not found", responseCode = "400",content = @Content)
    })
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal){
        Project project = projectService.findProjectByIdentifier(projectId, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
	
	
	@GetMapping("/all")
	@Operation(summary = "Get all projects from the current user")
    public Iterable<Project> getAllProjects(Principal principal){return projectService.findAllProjects(principal.getName());}
	
	
	/*
	 * Need to create a pathway for updating a project using PUT
	 * @PutMapping("/{projectId}")
	 */
	
	
	@DeleteMapping("/{projectId}")
	@Operation(summary = "Delete a project")
	public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal){
		
		projectService.deleteProjectByIdentifier(projectId.toUpperCase(), principal.getName());
	    return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
	}
	
}
