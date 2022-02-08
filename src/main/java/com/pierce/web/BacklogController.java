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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pierce.domain.ProjectTask;
import com.pierce.services.MapValidationErrorService;
import com.pierce.services.ProjectTaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin //be careful with this
@Tag(name = "Backlog")
public class BacklogController {
	
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
    private MapValidationErrorService mapValidationErrorService;
	
	
	@PostMapping("/{backlog_id}")
	@Operation(summary = "Add project task to backlog")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask newProjectTask, 
    										BindingResult result, @PathVariable String backlog_id, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask = projectTaskService.addProjectTask(backlog_id, newProjectTask, principal.getName());

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.CREATED);

    }
	
	
	@GetMapping("/{backlog_id}")
	@Operation(summary = "Get project backlog")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id, Principal principal){

        return projectTaskService.findBacklogById(backlog_id, principal.getName());
    }
	
	@GetMapping("/{backlog_id}/{pt_id}")
	@Operation(summary = "Get project task")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
		
		ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id, principal.getName());
		return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
	}
	
	
	@PatchMapping("/{backlog_id}/{pt_id}")
	@Operation(summary = "Update a specific project task")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask updatedTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id, principal.getName());

        return new ResponseEntity<ProjectTask>(updatedTask, HttpStatus.OK);

    }
	
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	@Operation(summary = "Delete a project task")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id, Principal principal){
		
        projectTaskService.deletePTByProjectSequence(backlog_id, pt_id, principal.getName());
        return new ResponseEntity<String>("Project Task "+ pt_id +" was deleted successfully", HttpStatus.OK);
    }
}
