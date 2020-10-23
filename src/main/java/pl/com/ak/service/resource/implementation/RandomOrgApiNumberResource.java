package pl.com.ak.service.resource.implementation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ak.client.RandomOrgApiClient;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.service.resource.NumberResource;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RandomOrgApiNumberResource implements NumberResource {

  private static final int count = 0;
  private static final int numberColumns = 1;
  private static final int baseNumber = 10;
  private static final String formatOutput = "plain";
  private static final String rndMethod = "new";
  private static final String splitNumber = "\\s";

  private final RandomOrgApiClient randomOrgApiClient;

  @Override
  public List<Integer> getNumbersFromResource(final ParameterDto parameterDto) {
    if (parameterDto.getCount().equals(count)) {
      return Collections.emptyList();
    }
    final String numbers = randomOrgApiClient.getRandomIntegers(
        parameterDto.getCount(), parameterDto.getMin(), parameterDto.getMax(),
        numberColumns, baseNumber, formatOutput, rndMethod
    );

    return parseNumbers(numbers);
  }

  private List<Integer> parseNumbers(final String numbers) {
    return Arrays.stream(numbers.split(splitNumber))
        .map(Integer::parseInt)
        .collect(Collectors.toUnmodifiableList());
  }
}
