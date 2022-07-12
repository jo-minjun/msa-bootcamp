package com.vroong.msabootcamp.api;

import static com.vroong.msabootcamp.config.Constants.V1_MEDIA_TYPE;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.api.mockmvc.CustomMockMvc;
import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import com.vroong.msabootcamp.api.model.SingerDto;
import com.vroong.msabootcamp.service.SingerService;
import com.vroong.msabootcamp.support.TestUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Import(value = CustomMockMvc.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName(value = "Singer 컨트롤러")
class SingerApiDelegateImplTest {

  MockMvc mockMvc;
  @Autowired
  CustomMockMvc customMockMvc;
  @Autowired
  SingerApiDelegate apiDelegate;
  @MockBean
  SingerService singerService;

  @Test
  @WithMockUser(value = "test")
  void createSinger() throws Exception {
    final CreateSingerRequestDto createSingerRequestDto = new CreateSingerRequestDto()
        .name("지코");
    final long id = 1L;
    when(singerService.createSinger(createSingerRequestDto))
        .thenReturn(new SingerDto().singerId(id));

    final ResultActions res = mockMvc.perform(
        post("/api/singers")
            .contentType(V1_MEDIA_TYPE)
            .characterEncoding("utf-8")
            .content(TestUtils.convertObjectToString(createSingerRequestDto))
    ).andDo(print());

    res.andExpect(status().isCreated());
    res.andExpect(header().string("Location", Matchers.endsWith(String.valueOf(id))));
  }

  @BeforeEach
  void setup() {
    this.mockMvc = customMockMvc.getMockMvc(new SingerApiController(apiDelegate));
  }
}