package com.pierce.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.ProjectTask;
import com.pierce.domain.User;
import com.pierce.exceptions.ProjectNotFoundException;
import com.pierce.repositories.BacklogRepository;
import com.pierce.repositories.ProjectRepository;
import com.pierce.repositories.ProjectTaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProjectTaskService.class})
@ExtendWith(SpringExtension.class)
class ProjectTaskServiceTest {
    @MockBean
    private BacklogRepository backlogRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectTaskService projectTaskService;

    @Test
    void testFindBacklogById() {
        ArrayList<ProjectTask> projectTaskList = new ArrayList<>();
        when(this.projectTaskRepository.findByProjectIdentifierOrderByPriority((String) any())).thenReturn(projectTaskList);

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
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any())).thenReturn(project2);
        Iterable<ProjectTask> actualFindBacklogByIdResult = this.projectTaskService.findBacklogById("42", "janedoe");
        assertSame(projectTaskList, actualFindBacklogByIdResult);
        assertTrue(((Collection<ProjectTask>) actualFindBacklogByIdResult).isEmpty());
        verify(this.projectTaskRepository).findByProjectIdentifierOrderByPriority((String) any());
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }

    @Test
    void testFindBacklogById2() {
        when(this.projectTaskRepository.findByProjectIdentifierOrderByPriority((String) any()))
                .thenReturn(new ArrayList<>());
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any()))
                .thenThrow(new ProjectNotFoundException("An error occurred"));
        assertThrows(ProjectNotFoundException.class, () -> this.projectTaskService.findBacklogById("42", "janedoe"));
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }

    @Test
    void testFindPTByProjectSequence() {
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

        ProjectTask projectTask = new ProjectTask();
        projectTask.setAcceptanceCriteria("Acceptance Criteria");
        projectTask.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setCreateAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setDueDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        projectTask.setId(123L);
        projectTask.setPriority(1);
        projectTask.setProjectIdentifier("myproject");
        projectTask.setProjectSequence("Project Sequence");
        projectTask.setStatus("Status");
        projectTask.setSummary("Summary");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setUpdateAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.projectTaskRepository.findByProjectSequence((String) any())).thenReturn(projectTask);

        Project project2 = new Project();
        project2.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(new User());

        Backlog backlog2 = new Backlog();
        backlog2.setId(123L);
        backlog2.setPTSequence(1);
        backlog2.setProject(project2);
        backlog2.setProjectIdentifier("myproject");
        backlog2.setProjectTasks(new ArrayList<>());

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project3 = new Project();
        project3.setBacklog(backlog2);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setCreatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setEndDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setId(123L);
        project3.setProjectIdentifier("myproject");
        project3.setProjectLeader("Project Leader");
        project3.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setStartDate(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setUser(user1);

        Backlog backlog3 = new Backlog();
        backlog3.setId(123L);
        backlog3.setPTSequence(1);
        backlog3.setProject(project3);
        backlog3.setProjectIdentifier("myproject");
        backlog3.setProjectTasks(new ArrayList<>());

        User user2 = new User();
        user2.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setFullName("Dr Jane Doe");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setUsername("janedoe");

        Project project4 = new Project();
        project4.setBacklog(backlog3);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setCreatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setEndDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setId(123L);
        project4.setProjectIdentifier("myproject");
        project4.setProjectLeader("Project Leader");
        project4.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setStartDate(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setUser(user2);
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any())).thenReturn(project4);
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectTaskService.findPTByProjectSequence("Backlog id", "Pt id", "janedoe"));
        verify(this.projectTaskRepository).findByProjectSequence((String) any());
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }

    @Test
    void testFindPTByProjectSequence2() {
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

        ProjectTask projectTask = new ProjectTask();
        projectTask.setAcceptanceCriteria("Acceptance Criteria");
        projectTask.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setCreateAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setDueDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        projectTask.setId(123L);
        projectTask.setPriority(1);
        projectTask.setProjectIdentifier("myproject");
        projectTask.setProjectSequence("Project Sequence");
        projectTask.setStatus("Status");
        projectTask.setSummary("Summary");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setUpdateAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.projectTaskRepository.findByProjectSequence((String) any())).thenReturn(projectTask);
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any()))
                .thenThrow(new ProjectNotFoundException("An error occurred"));
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectTaskService.findPTByProjectSequence("Backlog id", "Pt id", "janedoe"));
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }

    @Test
    void testUpdateByProjectSequence() {
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

        ProjectTask projectTask = new ProjectTask();
        projectTask.setAcceptanceCriteria("Acceptance Criteria");
        projectTask.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setCreateAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setDueDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        projectTask.setId(123L);
        projectTask.setPriority(1);
        projectTask.setProjectIdentifier("myproject");
        projectTask.setProjectSequence("Project Sequence");
        projectTask.setStatus("Status");
        projectTask.setSummary("Summary");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setUpdateAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.projectTaskRepository.findByProjectSequence((String) any())).thenReturn(projectTask);

        Project project2 = new Project();
        project2.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(new User());

        Backlog backlog2 = new Backlog();
        backlog2.setId(123L);
        backlog2.setPTSequence(1);
        backlog2.setProject(project2);
        backlog2.setProjectIdentifier("myproject");
        backlog2.setProjectTasks(new ArrayList<>());

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project3 = new Project();
        project3.setBacklog(backlog2);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setCreatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setEndDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setId(123L);
        project3.setProjectIdentifier("myproject");
        project3.setProjectLeader("Project Leader");
        project3.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setStartDate(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setUser(user1);

        Backlog backlog3 = new Backlog();
        backlog3.setId(123L);
        backlog3.setPTSequence(1);
        backlog3.setProject(project3);
        backlog3.setProjectIdentifier("myproject");
        backlog3.setProjectTasks(new ArrayList<>());

        User user2 = new User();
        user2.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setFullName("Dr Jane Doe");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setUsername("janedoe");

        Project project4 = new Project();
        project4.setBacklog(backlog3);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setCreatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setEndDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setId(123L);
        project4.setProjectIdentifier("myproject");
        project4.setProjectLeader("Project Leader");
        project4.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setStartDate(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setUser(user2);
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any())).thenReturn(project4);

        Backlog backlog4 = new Backlog();
        backlog4.setId(123L);
        backlog4.setPTSequence(1);
        backlog4.setProject(new Project());
        backlog4.setProjectIdentifier("myproject");
        backlog4.setProjectTasks(new ArrayList<>());

        User user3 = new User();
        user3.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedAt(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setFullName("Dr Jane Doe");
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult30 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setUpdatedAt(Date.from(atStartOfDayResult30.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setUsername("janedoe");

        Project project5 = new Project();
        project5.setBacklog(backlog4);
        LocalDateTime atStartOfDayResult31 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project5.setCreatedAt(Date.from(atStartOfDayResult31.atZone(ZoneId.of("UTC")).toInstant()));
        project5.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult32 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project5.setEndDate(Date.from(atStartOfDayResult32.atZone(ZoneId.of("UTC")).toInstant()));
        project5.setId(123L);
        project5.setProjectIdentifier("myproject");
        project5.setProjectLeader("Project Leader");
        project5.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult33 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project5.setStartDate(Date.from(atStartOfDayResult33.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult34 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project5.setUpdatedAt(Date.from(atStartOfDayResult34.atZone(ZoneId.of("UTC")).toInstant()));
        project5.setUser(user3);

        Backlog backlog5 = new Backlog();
        backlog5.setId(123L);
        backlog5.setPTSequence(1);
        backlog5.setProject(project5);
        backlog5.setProjectIdentifier("myproject");
        backlog5.setProjectTasks(new ArrayList<>());

        ProjectTask projectTask1 = new ProjectTask();
        projectTask1.setAcceptanceCriteria("Acceptance Criteria");
        projectTask1.setBacklog(backlog5);
        LocalDateTime atStartOfDayResult35 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask1.setCreateAt(Date.from(atStartOfDayResult35.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult36 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask1.setDueDate(Date.from(atStartOfDayResult36.atZone(ZoneId.of("UTC")).toInstant()));
        projectTask1.setId(123L);
        projectTask1.setPriority(1);
        projectTask1.setProjectIdentifier("myproject");
        projectTask1.setProjectSequence("Project Sequence");
        projectTask1.setStatus("Status");
        projectTask1.setSummary("Summary");
        LocalDateTime atStartOfDayResult37 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask1.setUpdateAt(Date.from(atStartOfDayResult37.atZone(ZoneId.of("UTC")).toInstant()));
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectTaskService.updateByProjectSequence(projectTask1, "Backlog id", "Pt id", "janedoe"));
        verify(this.projectTaskRepository).findByProjectSequence((String) any());
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }

    @Test
    void testDeletePTByProjectSequence() {
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

        ProjectTask projectTask = new ProjectTask();
        projectTask.setAcceptanceCriteria("Acceptance Criteria");
        projectTask.setBacklog(backlog1);
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setCreateAt(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setDueDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        projectTask.setId(123L);
        projectTask.setPriority(1);
        projectTask.setProjectIdentifier("myproject");
        projectTask.setProjectSequence("Project Sequence");
        projectTask.setStatus("Status");
        projectTask.setSummary("Summary");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        projectTask.setUpdateAt(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.projectTaskRepository.findByProjectSequence((String) any())).thenReturn(projectTask);

        Project project2 = new Project();
        project2.setBacklog(new Backlog());
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setCreatedAt(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setEndDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setId(123L);
        project2.setProjectIdentifier("myproject");
        project2.setProjectLeader("Project Leader");
        project2.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setStartDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project2.setUpdatedAt(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        project2.setUser(new User());

        Backlog backlog2 = new Backlog();
        backlog2.setId(123L);
        backlog2.setPTSequence(1);
        backlog2.setProject(project2);
        backlog2.setProjectIdentifier("myproject");
        backlog2.setProjectTasks(new ArrayList<>());

        User user1 = new User();
        user1.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedAt(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setFullName("Dr Jane Doe");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setUpdatedAt(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setUsername("janedoe");

        Project project3 = new Project();
        project3.setBacklog(backlog2);
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setCreatedAt(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setEndDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setId(123L);
        project3.setProjectIdentifier("myproject");
        project3.setProjectLeader("Project Leader");
        project3.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setStartDate(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project3.setUpdatedAt(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        project3.setUser(user1);

        Backlog backlog3 = new Backlog();
        backlog3.setId(123L);
        backlog3.setPTSequence(1);
        backlog3.setProject(project3);
        backlog3.setProjectIdentifier("myproject");
        backlog3.setProjectTasks(new ArrayList<>());

        User user2 = new User();
        user2.setConfirmPassword("iloveyou");
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedAt(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setFullName("Dr Jane Doe");
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setProjects(new ArrayList<>());
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setUpdatedAt(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setUsername("janedoe");

        Project project4 = new Project();
        project4.setBacklog(backlog3);
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setCreatedAt(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setEndDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setId(123L);
        project4.setProjectIdentifier("myproject");
        project4.setProjectLeader("Project Leader");
        project4.setProjectName("Project Name");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setStartDate(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        project4.setUpdatedAt(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        project4.setUser(user2);
        when(this.projectService.findProjectByIdentifier((String) any(), (String) any())).thenReturn(project4);
        assertThrows(ProjectNotFoundException.class,
                () -> this.projectTaskService.deletePTByProjectSequence("Backlog id", "Pt id", "janedoe"));
        verify(this.projectTaskRepository).findByProjectSequence((String) any());
        verify(this.projectService).findProjectByIdentifier((String) any(), (String) any());
    }
}

