package com.vroong.msabootcamp.api.mockmvc;

import com.vroong.msabootcamp.api.error.ExceptionTranslator;
import com.vroong.msabootcamp.support.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.filter.CharacterEncodingFilter;

@TestComponent
public class CustomMockMvc {

  @Autowired
  ExceptionTranslator exceptionTranslator;
  @Autowired
  MappingJackson2HttpMessageConverter jacksonMessageConverter;
  @Autowired
  @Qualifier(value = "defaultValidator")
  Validator validator;

  public MockMvc getMockMvc(Object... controller) {
    return MockMvcBuilders.standaloneSetup(controller)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtils.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter)
        .setValidator(validator)
        .addFilters(new CharacterEncodingFilter("utf-8", true))
        .build();
  }
}
