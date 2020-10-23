package pl.com.ak.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ParameterDto {

  final Integer count;
  final Integer max;
  final Integer min;
}
