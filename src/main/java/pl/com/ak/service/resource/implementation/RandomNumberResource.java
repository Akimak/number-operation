package pl.com.ak.service.resource.implementation;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.service.resource.NumberResource;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RandomNumberResource implements NumberResource {

  @Override
  public Collection<Number> getNumbersFromResource(final ParameterDto parameter) {
    return new Random().ints(parameter.getCount(), parameter.getMin(), parameter.getMax()).boxed()
        .collect(Collectors.toUnmodifiableList());
  }
}
