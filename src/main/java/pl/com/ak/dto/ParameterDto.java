package pl.com.ak.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParameterDto {

  private final Integer count;
  private final Integer max;
  private final Integer min;
}
