package com.vroong.msabootcamp.api;

import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_NUMBER;
import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_SIZE;
import static com.vroong.msabootcamp.config.Constants.V1_MEDIA_TYPE;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.api.fixtures.Fixture;
import com.vroong.msabootcamp.api.mockmvc.CustomMockMvc;
import com.vroong.msabootcamp.api.model.AlbumDto;
import com.vroong.msabootcamp.api.model.AlbumListDto;
import com.vroong.msabootcamp.api.model.CreateAlbumRequestDto;
import com.vroong.msabootcamp.service.AlbumService;
import com.vroong.msabootcamp.support.PaginationUtils;
import com.vroong.msabootcamp.support.TestUtils;
import java.time.OffsetDateTime;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Import(value = CustomMockMvc.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayName(value = "Album 컨트롤러")
class AlbumApiDelegateImplTest {

  MockMvc mockMvc;
  @Autowired
  CustomMockMvc customMockMvc;
  @Autowired
  AlbumApiDelegate apiDelegate;
  @MockBean
  AlbumService albumService;

  @Test
  @WithMockUser(value = "test")
  void createAlbum() throws Exception {
    final CreateAlbumRequestDto content = new CreateAlbumRequestDto()
        .singerId(1L)
        .publishedAt(OffsetDateTime.now())
        .title("아무 노래");
    final long id = 1L;
    when(albumService.createAlbum(content))
        .thenReturn(new AlbumDto().albumId(id));

    final ResultActions res = mockMvc.perform(
        post("/api/albums")
            .contentType(V1_MEDIA_TYPE)
            .content(TestUtils.convertObjectToString(content))
            .characterEncoding("utf-8")
    ).andDo(print());

    res.andExpect(status().isCreated());
    res.andExpect(header().string("Location", Matchers.endsWith(String.valueOf(id))));
  }

  @Test
  @WithMockUser(value = "test")
  void listAlbums() throws Exception {
    final Pageable pageable = PaginationUtils.getPageable(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    final AlbumDto albumDto = Fixture.anAlbumDto();
    when(albumService.listAlbums(pageable))
        .thenReturn(new AlbumListDto()
            .data(Collections.singletonList(albumDto))
            .page(Fixture.aPageDto())
        );

    final ResultActions res = mockMvc.perform(
        get("/api/albums")
            .accept(V1_MEDIA_TYPE)
            .param("size", String.valueOf(DEFAULT_PAGE_SIZE))
            .param("page", String.valueOf(DEFAULT_PAGE_NUMBER))
    ).andDo(print());

    res.andExpect(status().isOk());
    res.andExpect(jsonPath("$.data").isArray());
    res.andExpect(jsonPath("$.page.size", equalTo(DEFAULT_PAGE_SIZE)));
    res.andExpect(jsonPath("$.page.number", equalTo(DEFAULT_PAGE_NUMBER)));
  }

  @BeforeEach
  void setup() {
    this.mockMvc = customMockMvc.getMockMvc(new AlbumApiController(apiDelegate));
  }
}