package com.vroong.msabootcamp.repository;

import com.vroong.msabootcamp.domain.Example;
import java.util.List;

public interface ExampleRepositoryCustom {

  List<Example> findCreatedToday();
}
