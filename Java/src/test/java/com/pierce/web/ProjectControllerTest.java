package com.pierce.web;

import com.pierce.domain.Backlog;
import com.pierce.domain.Project;
import com.pierce.domain.User;
import com.pierce.services.MapValidationErrorService;
import com.pierce.services.ProjectService;
import com.sun.security.auth.UserPrincipal;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
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

@ContextConfiguration(classes = {ProjectController.class})
@ExtendWith(SpringExtension.class)
class ProjectControllerTest {
    @MockBean
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private ProjectController projectController;

    @MockBean
    private ProjectService projectService;

    @Test
    void testCreateNewProject() throws Exception {
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
    }

    @Test
    void testDeleteProject() throws Exception {
        doNothing().when(this.projectService).deleteProjectByIdentifier((String) any(), (String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/project/{projectId}", "myproject");
        deleteResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.projectController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Project with ID: 'myproject' was deleted"));
    }

    @Test
    void testGetAllProjects() throws Exception {
        when(this.projectService.findAllProjects((String) any())).thenReturn((Iterable<Project>) mock(Iterable.class));
        org.apache.catalina.User user = mock(org.apache.catalina.User.class);
        when(user.getName()).thenReturn("Name");
        UserDatabaseRealm.UserDatabasePrincipal principal = new UserDatabaseRealm.UserDatabasePrincipal(user,
                new MemoryUserDatabase());

        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/project/all");
        getResult.accept("https://example.org/example");
        getResult.principal(principal);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.projectController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    @Test
    void testGetProjectById() throws Exception {
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
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/project/{projectId}", "myproject");
        getResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.projectController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"projectName\":\"Project Name\",\"projectIdentifier\":\"myproject\",\"description\":\"The characteristics"
                                        + " of someone or something\",\"startDate\":\"1970-00-01\",\"endDate\":\"1970-00-01\",\"createdAt\":\"1970-00-01\","
                                        + "\"updatedAt\":\"1970-00-01\",\"projectLeader\":\"Project Leader\"}"));
    }
}

