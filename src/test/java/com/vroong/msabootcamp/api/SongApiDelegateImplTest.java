package com.vroong.msabootcamp.api;

import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_NUMBER;
import static com.vroong.msabootcamp.api.fixtures.Fixture.DEFAULT_PAGE_SIZE;
import static com.vroong.msabootcamp.config.Constants.V1_MEDIA_TYPE;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vroong.msabootcamp.api.fixtures.Fixture;
import com.vroong.msabootcamp.api.mockmvc.CustomMockMvc;
import com.vroong.msabootcamp.api.model.CreateSongRequestDto;
import com.vroong.msabootcamp.api.model.SongDto;
import com.vroong.msabootcamp.api.model.SongListDto;
import com.vroong.msabootcamp.service.SongService;
import com.vroong.msabootcamp.support.PaginationUtils;
import com.vroong.msabootcamp.support.TestUtils;
import java.util.Collections;
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
@DisplayName(value = "Song 컨트롤러")
class SongApiDelegateImplTest {

  MockMvc mockMvc;
  @Autowired
  CustomMockMvc customMockMvc;
  @Autowired
  SongApiDelegate apiDelegate;
  @MockBean
  SongService songService;

  @Test
  @WithMockUser(value = "test")
  void createSong() throws Exception {
    final long id = 1L;
    final CreateSongRequestDto content = new CreateSongRequestDto()
        .albumId(id)
        .title("흐르는 강물을 거슬러 오르는 연어")
        .playTime("03:33");
    when(songService.createSong(content))
        .thenReturn(new SongDto().songId(id));

    final ResultActions res = mockMvc.perform(
            post("/api/songs")
                .contentType(V1_MEDIA_TYPE)
                .characterEncoding("utf-8")
                .content(TestUtils.convertObjectToString(content))
        )
        .andDo(print());

    res.andExpect(status().isCreated());
    res.andExpect(header().string("Location", endsWith(String.valueOf(id))));
  }

  @Test
  @WithMockUser(value = "test")
  void deleteSong() throws Exception {
    final long id = 1L;
    doNothing().when(songService).deleteSong(id);

    final ResultActions res = mockMvc.perform(
            delete("/api/songs/{songId}", id)
        )
        .andDo(print());

    res.andExpect(status().isNoContent());
  }

  @Test
  @WithMockUser(value = "test")
  void getSong() throws Exception {
    final SongDto songDto = Fixture.aSongDto();
    final long id = songDto.getSongId();
    when(songService.getSong(id))
        .thenReturn(songDto);

    final ResultActions res = mockMvc.perform(
            get("/api/songs/{songId}", id)
                .accept(V1_MEDIA_TYPE)
        )
        .andDo(print());

    res.andExpect(status().isOk());

  }

  @Test
  @WithMockUser(value = "test")
  void listSongs() throws Exception {
    final Pageable pageable = PaginationUtils.getPageable(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    final SongListDto songListDto = new SongListDto()
        .data(Collections.singletonList(Fixture.aSongDto()))
        .page(Fixture.aPageDto());
    when(songService.listSongs(pageable))
        .thenReturn(songListDto);

    final ResultActions res = mockMvc.perform(
            get("/api/songs")
                .accept(V1_MEDIA_TYPE)
                .param("size", String.valueOf(DEFAULT_PAGE_SIZE))
                .param("page", String.valueOf(DEFAULT_PAGE_NUMBER))
        )
        .andDo(print());

    res.andExpect(status().isOk());
    res.andExpect(jsonPath("$.data").isArray());
    res.andExpect(jsonPath("$.page.size", equalTo(DEFAULT_PAGE_SIZE)));
    res.andExpect(jsonPath("$.page.number", equalTo(DEFAULT_PAGE_NUMBER)));
  }

  @Test
  @WithMockUser(value = "test")
  void updateSong() throws Exception {
    final CreateSongRequestDto content = new CreateSongRequestDto()
        .title("흐르는 강물을 거슬러 오르는 연어")
        .playTime("03:33");
    final long id = 1L;
    doNothing().when(songService).updateSong(id, content);

    final ResultActions res = mockMvc.perform(
            put("/api/songs/{songId}", id)
                .contentType(V1_MEDIA_TYPE)
                .characterEncoding("utf-8")
                .content(TestUtils.convertObjectToString(content))
        )
        .andDo(print());

    res.andExpect(status().isNoContent());
  }

  @BeforeEach
  void setup() {
    this.mockMvc = customMockMvc.getMockMvc(new SongApiController(apiDelegate));
  }
}