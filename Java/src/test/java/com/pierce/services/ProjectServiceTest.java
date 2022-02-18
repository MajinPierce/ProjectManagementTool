package com.pierce.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.User;
import com.pierce.exceptions.ProjectNotFoundException;
import com.pierce.repositories.BacklogRepository;
import com.pierce.repositories.ProjectRepository;
import com.pierce.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectService.class})
@ExtendWith(SpringExtension.class)
class ProjectServiceTest {
    @MockBean
    private BacklogRepository backlogRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testSaveOrUpdateProject() {
        // create new project and set properties, set owner to uninitialized user
        Project project = new Project();
        project.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        project.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        project.setId(123L);
        project.setProjectIdentifier("myproject");
        project.setProjectLeader("Project Leader");
        project.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        project.setUser(new User());

        // create new backlog, set properties, assign to project
        Backlog backlog = new Backlog();
        backlog.setId(123L);
        backlog.setPTSequence(1);
        backlog.setProject(project);
        backlog.setProjectIdentifier("myproject");
        backlog.setProjectTasks(new ArrayList<>());

        // create new user, set properties
        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        // create another project and set properties, set owner to user
        Project project1 = new Project();
        project1.setBacklog(backlog);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setEndDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setId(123L);
        project1.setProjectIdentifier("myproject");
        project1.setProjectLeader("Project Leader");
        project1.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setStartDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setUser(user);

        // create backlog for project1
        Backlog backlog1 = new Backlog();
        backlog1.setId(123L);
        backlog1.setPTSequence(1);
        backlog1.setProject(project1);
        backlog1.setProjectIdentifier("myproject");
        backlog1.setProjectTasks(new ArrayList<>());

        // create another user
        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project2 = new Project();
        project2.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(user1);
        when(this.projectRepository.findByProjectIdentifier((String) any())).thenReturn(project2);

        Backlog backlog2 = new Backlog();
        backlog2.setId(123L);
        backlog2.setPTSequence(1);
        backlog2.setProject(new Project());
        backlog2.setProjectIdentifier("myproject");
        backlog2.setProjectTasks(new ArrayList<>());

        User user2 = new User();
        user2.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setFullName("Dr Jane Doe");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setUsername("janedoe");

        Project project3 = new Project();
        project3.setBacklog(backlog2);
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setCreatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setEndDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setId(123L);
        project3.setProjectIdentifier("myproject");
        project3.setProjectLeader("Project Leader");
        project3.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setStartDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setUpdatedAt(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setUser(user2);

        Backlog backlog3 = new Backlog();
        backlog3.setId(123L);
        backlog3.setPTSequence(1);
        backlog3.setProject(project3);
        backlog3.setProjectIdentifier("myproject");
        backlog3.setProjectTasks(new ArrayList<>());

        User user3 = new User();
        user3.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setFullName("Dr Jane Doe");
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setUsername("janedoe");

        Project project4 = new Project();
        project4.setBacklog(backlog3);
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setCreatedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setEndDate(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setId(123L);
        project4.setProjectIdentifier("myproject");
        project4.setProjectLeader("Project Leader");
        project4.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setStartDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setUpdatedAt(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setUser(user3);
        assertThrows(ProjectNotFoundException.class, () -> this.projectService.saveOrUpdateProject(project4, "janedoe"));
        verify(this.projectRepository).findByProjectIdentifier((String) any());
    }

    @Test
    void testFindProjectByIdentifier() {
        Project project = new Project();
        project.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        project.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        project.setId(123L);
        project.setProjectIdentifier("myproject");
        project.setProjectLeader("Project Leader");
        project.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        project.setUser(new User());

        Backlog backlog = new Backlog();
        backlog.setId(123L);
        backlog.setPTSequence(1);
        backlog.setProject(project);
        backlog.setProjectIdentifier("myproject");
        backlog.setProjectTasks(new ArrayList<>());

        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        Project project1 = new Project();
        project1.setBacklog(backlog);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setEndDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setId(123L);
        project1.setProjectIdentifier("myproject");
        project1.setProjectLeader("Project Leader");
        project1.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setStartDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setUser(user);

        Backlog backlog1 = new Backlog();
        backlog1.setId(123L);
        backlog1.setPTSequence(1);
        backlog1.setProject(project1);
        backlog1.setProjectIdentifier("myproject");
        backlog1.setProjectTasks(new ArrayList<>());

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project2 = new Project();
        project2.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(user1);
        when(this.projectRepository.findByProjectIdentifier((String) any())).thenReturn(project2);
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectService.findProjectByIdentifier("myproject", "janedoe"));
        verify(this.projectRepository).findByProjectIdentifier((String) any());
    }

    // test find all projects
    // make call by random username to mock project repo
    // return mock iterable upon success and verify
    @Test
    void testFindAllProjects() {
        when(this.projectRepository.findAllByProjectLeader((String) any()))
                .thenReturn((Iterable<Project>) mock(Iterable.class));
        this.projectService.findAllProjects("janedoe");
        verify(this.projectRepository).findAllByProjectLeader((String) any());
    }

    // test find all projects
    // make call by random username to mock project repo
    // throw project not found exception and verify
    @Test
    void testFindAllProjects2() {
        when(this.projectRepository.findAllByProjectLeader((String) any()))
                .thenThrow(new ProjectNotFoundException("An error occurred"));
        assertThrows(ProjectNotFoundException.class, () -> this.projectService.findAllProjects("janedoe"));
        verify(this.projectRepository).findAllByProjectLeader((String) any());
    }

    @Test
    void testDeleteProjectByIdentifier() {
        Project project = new Project();
        project.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setCreatedAt(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        project.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setEndDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        project.setId(123L);
        project.setProjectIdentifier("myproject");
        project.setProjectLeader("Project Leader");
        project.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setStartDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project.setUpdatedAt(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        project.setUser(new User());

        Backlog backlog = new Backlog();
        backlog.setId(123L);
        backlog.setPTSequence(1);
        backlog.setProject(project);
        backlog.setProjectIdentifier("myproject");
        backlog.setProjectTasks(new ArrayList<>());

        User user = new User();
        user.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedAt(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user.setFullName("Dr Jane Doe");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setUpdatedAt(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user.setUsername("janedoe");

        Project project1 = new Project();
        project1.setBacklog(backlog);
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setCreatedAt(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setEndDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setId(123L);
        project1.setProjectIdentifier("myproject");
        project1.setProjectLeader("Project Leader");
        project1.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setStartDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project1.setUpdatedAt(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        project1.setUser(user);

        Backlog backlog1 = new Backlog();
        backlog1.setId(123L);
        backlog1.setPTSequence(1);
        backlog1.setProject(project1);
        backlog1.setProjectIdentifier("myproject");
        backlog1.setProjectTasks(new ArrayList<>());

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project2 = new Project();
        project2.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(user1);
        when(this.projectRepository.findByProjectIdentifier((String) any())).thenReturn(project2);
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectService.deleteProjectByIdentifier("myproject", "janedoe"));
        verify(this.projectRepository).findByProjectIdentifier((String) any());
    }
}

