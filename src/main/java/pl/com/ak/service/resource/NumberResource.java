package pl.com.ak.service.resource;

import java.util.Collection;
import pl.com.ak.dto.ParameterDto;

public interface NumberResource {

  Collection<? extends Number> getNumbersFromResource(ParameterDto parameterDto);
}
