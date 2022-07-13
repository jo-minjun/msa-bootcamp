package com.vroong.msabootcamp.domain;

import com.vroong.msabootcamp.api.model.CreateSingerRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "singers")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "name", "albums"})
@EqualsAndHashCode(of = {"id"})
public class Singer extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 64)
  private String name;

  @OneToMany(mappedBy = "singer", fetch = FetchType.LAZY)
  private List<Album> albums = new ArrayList<>();

  @Builder
  private Singer(Long id, String name) {
    this(name);
    this.id = id;
  }

  @Builder
  private Singer(String name) {
    this.name = name;
  }

  public static Singer createFrom(CreateSingerRequestDto dto) {
    return new Singer(dto.getName());
  }
}
