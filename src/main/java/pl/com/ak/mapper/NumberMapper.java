package pl.com.ak.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.com.ak.dto.NumberDto;
import pl.com.ak.enity.Number;

@Service
public class NumberMapper {

  public List<NumberDto> map(final List<Number> numbers) {
    return numbers.stream().map(this::map).collect(Collectors.toUnmodifiableList());
  }

  private NumberDto map(final Number number) {
    return NumberDto.builder()
        .id(number.getId())
        .value(number.getValue())
        .build();
  }

}