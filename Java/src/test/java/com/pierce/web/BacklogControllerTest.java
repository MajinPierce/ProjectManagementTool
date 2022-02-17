package com.pierce.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.ProjectTask;
import com.pierce.domain.User;
import com.pierce.services.MapValidationErrorService;
import com.pierce.services.ProjectTaskService;
import com.sun.security.auth.UserPrincipal;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BacklogController.class})
@ExtendWith(SpringExtension.class)
class BacklogControllerTest {
    @Autowired
    private BacklogController backlogController;

    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    @MockBean
    private ProjectTaskService projectTaskService;

    @Test
    void testAddPTtoBacklog() throws Exception {
        when(this.mapValidationErrorService.MapValidationService((org.springframework.validation.BindingResult) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

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
        String content = (new ObjectMapper()).writeValueAsString(projectTask);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/backlog/{backlog_id}", "Backlog id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.backlogController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    void testDeleteProjectTask() throws Exception {
        doNothing().when(this.projectTaskService).deletePTByProjectSequence((String) any(), (String) any(), (String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/backlog/{backlog_id}/{pt_id}",
                "Backlog id", "Pt id");
        deleteResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.backlogController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Project Task Pt id was deleted successfully"));
    }

    @Test
    void testGetProjectBacklog() throws Exception {
        when(this.projectTaskService.findBacklogById((String) any(), (String) any()))
                .thenReturn((Iterable<ProjectTask>) mock(Iterable.class));
        org.apache.catalina.User user = mock(org.apache.catalina.User.class);
        when(user.getName()).thenReturn("Name");
        UserDatabaseRealm.UserDatabasePrincipal principal = new UserDatabaseRealm.UserDatabasePrincipal(user,
                new MemoryUserDatabase());

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/backlog/{backlog_id}", "Backlog id");
        getResult.accept("https://example.org/example");
        getResult.principal(principal);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.backlogController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testGetProjectTask() throws Exception {
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
        when(this.projectTaskService.findPTByProjectSequence((String) any(), (String) any(), (String) any()))
                .thenReturn(projectTask);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/backlog/{backlog_id}/{pt_id}",
                "Backlog id", "Pt id");
        getResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.backlogController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"projectSequence\":\"Project Sequence\",\"summary\":\"Summary\",\"acceptanceCriteria\":\"Acceptance"
                                        + " Criteria\",\"status\":\"Status\",\"priority\":1,\"dueDate\":\"1970-00-01\",\"projectIdentifier\":\"myproject\","
                                        + "\"createAt\":\"1970-00-01\",\"updateAt\":\"1970-00-01\"}"));
    }

    @Test
    void testUpdateProjectTask() throws Exception {
        when(this.mapValidationErrorService.MapValidationService((org.springframework.validation.BindingResult) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

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
        String content = (new ObjectMapper()).writeValueAsString(projectTask);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/backlog/{backlog_id}/{pt_id}", "Backlog id", "Pt id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.backlogController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

