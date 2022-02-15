package com.pierce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.ProjectTask;
import com.pierce.exceptions.ProjectNotFoundException;
import com.pierce.repositories.BacklogRepository;
import com.pierce.repositories.ProjectRepository;
import com.pierce.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private  ProjectRepository projectRepository;
	
	@Autowired
    private ProjectService projectService;

	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
		//tasks added to specific project backlog
		Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
		projectTask.setBacklog(backlog);
			
		// set up project sequence
		Integer backlogSequence = backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
			
		//add sequence to project task
		projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
			
		//default task priority
		if(projectTask.getPriority() == null || projectTask.getPriority() == 0) {
			projectTask.setPriority(3);
		}
			
		//default project status
		//enum statuses maybe later
		if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TO_DO");
		}
			
		//persist and return project task
		return projectTaskRepository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findBacklogById(String id, String username){
		
		projectService.findProjectByIdentifier(id, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
	
	public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username){
		
		projectService.findProjectByIdentifier(backlog_id, username);
		
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		
		if(projectTask == null) {
			throw  new ProjectNotFoundException("Project task '" + pt_id + "' not found");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("Project task '" + pt_id + "' not found in project '" + backlog_id + "'");
		}

        return projectTask;
    }
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id, String username){
        
		ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
        projectTask = updatedTask;
        
        return projectTaskRepository.save(projectTask);
    }
	
	public void deletePTByProjectSequence(String backlog_id, String pt_id, String username){
		
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
        projectTaskRepository.delete(projectTask);
    }
}
