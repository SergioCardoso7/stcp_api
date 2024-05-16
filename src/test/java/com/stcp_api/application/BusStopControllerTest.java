package com.stcp_api.application;


import static com.stcp_api.TestUtil.EMPTY_COORDINATES;
import static com.stcp_api.TestUtil.MOCK_STRING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.services.BusStopService;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = BusStopController.class)
class BusStopControllerTest {

  @Autowired
  BusStopController controller;

  @MockBean
  BusStopService busStopService;

  @Test
  void test_getStopsByTerm() throws IOException {
    when(busStopService.getBusStopBySearchTerm(anyString()))
        .thenReturn(
            List.of(new BusStopDTO(MOCK_STRING, MOCK_STRING, MOCK_STRING, EMPTY_COORDINATES)));
    var result = controller.getStopsByTerm(MOCK_STRING);
    assertThat(result).isNotNull();
    verify(busStopService).getBusStopBySearchTerm(anyString());
  }
}
