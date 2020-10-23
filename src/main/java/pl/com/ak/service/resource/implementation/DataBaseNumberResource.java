package pl.com.ak.service.resource.implementation;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.ak.dto.NumberDto;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.mapper.NumberMapper;
import pl.com.ak.repository.NumberRepository;
import pl.com.ak.service.resource.NumberResource;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataBaseNumberResource implements NumberResource {

  private static final int page = 0;

  private final NumberRepository numberRepository;
  private final NumberMapper numberMapper;

  @Override
  @Transactional(readOnly = true)
  public Collection<Number> getNumbersFromResource(final ParameterDto parameter) {
    return getNumbers(parameter).stream().map(number -> number.getValue())
        .collect(Collectors.toUnmodifiableList());
  }

  private List<NumberDto> getNumbers(final ParameterDto parameter) {
    return numberMapper.map(numberRepository
        .findAllByValueBetween(BigDecimal.valueOf(parameter.getMin()),
            BigDecimal.valueOf(parameter.getMax()), PageRequest.of(page, parameter.getCount()))
        .toList());
  }
}