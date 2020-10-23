package pl.com.ak.service;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;
import static pl.com.ak.utils.Utils.splitIntoParts;

import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.service.operation.Operation;
import pl.com.ak.service.resource.NumberResource;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NumberService {

  private static final Integer defaultValue = 0;

  private final List<NumberResource> numberServices;
  private final Operation operation;

  public Number run(final ParameterDto parameter) {
    validParameterDto(parameter);
    final Number number = prepareNumber(parameter).stream().reduce(operation.getOperator())
        .orElse(defaultValue);
    log.info("number past operation: {}", number);
    return number;
  }

  private Collection<Number> prepareNumber(final ParameterDto parameterDto) {
    final Deque<Integer> counts = splitIntoParts(parameterDto.getCount(), numberServices.size());
    final Collection<Number> numbers = numberServices.parallelStream()
        .flatMap(numberService -> numberService.getNumbersFromResource(
            getParameterDto(counts.pop(), parameterDto.getMin(), parameterDto.getMax()))
            .stream()).collect(Collectors.toUnmodifiableList());
    log.info("all numbers: {}", numbers);
    return numbers;
  }

  private ParameterDto getParameterDto(final Integer count, final Integer min, final Integer max) {
    return ParameterDto.builder().min(min).max(max).count(count).build();
  }

  private void validParameterDto(final ParameterDto parameter) {
    notNull(parameter, "parameter should be not null");
    isTrue(parameter.getCount() > 0, "count should be greater than 0");
    isTrue(parameter.getMax() >= parameter.getMin(), "max should be greater or equal the min");
  }

}
