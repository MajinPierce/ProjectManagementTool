package com.pierce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.User;
import com.pierce.exceptions.ProjectIdException;
import com.pierce.exceptions.ProjectNotFoundException;
import com.pierce.repositories.BacklogRepository;
import com.pierce.repositories.ProjectRepository;
import com.pierce.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	public Project saveOrUpdateProject(Project project, String username) {
		
		if(project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null && (!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            } else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier() + "' doesn't exist");
            }
        }
		try {
			User user = userRepository.findByUsername(username);
			project.setUser(user);
            project.setProjectLeader(user.getUsername());
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			
			if(project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId() != null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier()));
			}
			
			return projectRepository.save(project);
			
		} catch (Exception e) {
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}
	}
	
	/*
	 * Need to make a method to properly update projects using put
	 * 
	 */
	
	public Project findProjectByIdentifier(String projectId, String username){
        //Only want to return the project if the user looking for it is the owner
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
        }

        if(!project.getProjectLeader().equals(username)){
            throw new ProjectNotFoundException("Project not found in your account");
        }
        
        return project;
    }

	
	public Iterable<Project> findAllProjects(String username){
		return projectRepository.findAllByProjectLeader(username);
	}
	
	public void deleteProjectByIdentifier(String projectId, String username) {		
		projectRepository.delete(findProjectByIdentifier(projectId, username));
	}
}
