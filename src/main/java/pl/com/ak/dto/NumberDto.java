package pl.com.ak.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NumberDto {

  private final Long id;
  private final BigDecimal value;
}
