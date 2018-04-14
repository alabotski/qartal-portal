package com.nomis.service;

import com.nomis.application.QartalPortalBootApplication;
import com.nomis.shared.response.ServerInfoResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ServerServiceTest.
 *
 * @author Aliaksei Labotski.
 * @since 4/14/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {QartalPortalBootApplication.class})
@ActiveProfiles("test")
public class ServerServiceTest {

  @Autowired
  private ServerService serverService;

  @Test
  public void should_serverInfo() throws IOException, URISyntaxException {
    ServerInfoResponse serverInfoResponse = serverService.getServerInfo();
    Assert.assertEquals(serverInfoResponse.getCount(), 2);
  }
}
