package pl.com.ak;

import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.service.NumberService;

@SpringBootTest
@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
class NumberOperationApplicationTests {

  @Autowired
  private final NumberService numberService;

  @Test
  void testService() {

    // given
    final ParameterDto parameterDto = getParameterDto(1, 3, 5);
    log.info("parameter for test: {}", parameterDto);

    // when
    final Number number = numberService.run(parameterDto);
    log.info("number for test: {}", number);

    // then
    assertTrue(number.floatValue() >= 0,
        "number should be not 0");
    assertTrue(number.floatValue() >= (parameterDto.getCount() * parameterDto.getMin()),
        "number should be greater then min * count");
    assertTrue(number.floatValue() <= (parameterDto.getCount() * parameterDto.getMax()),
        "number should be less then max * count");
  }

  private ParameterDto getParameterDto(int count, int min, int max) {
    return ParameterDto.builder().count(count).min(min).max(max).build();
  }


}
