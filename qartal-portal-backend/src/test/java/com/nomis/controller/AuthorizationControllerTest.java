package com.nomis.controller;

import static com.nomis.TestUtil.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nomis.service.AuthorizationService;
import com.nomis.shared.request.LoginRequest;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * AuthorizationControllerTest.
 *
 * @author Aliaksei Labotski.
 * @since 4/13/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppContext.class})
@WebAppConfiguration
public class AuthorizationControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AuthorizationService authorizationService;

  @InjectMocks
  private AuthorizationController authorizationController;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(authorizationController)
        .build();
  }

  @Test
  public void should_login() throws Exception {
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setLogin("Admin123");
    loginRequest.setPassword("Admin123");

    when(authorizationService.login(any())).thenReturn(true);

    mockMvc.perform(post("/authorization/login").contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(asJsonString(loginRequest))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.authorization", is(true)));

    verify(authorizationService, times(1)).login(refEq(loginRequest));
    verifyNoMoreInteractions(authorizationService);
  }

}
