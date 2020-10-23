package pl.com.ak.service.operation.implementation;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.ak.service.operation.Operation;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AddOperation implements Operation {

  @Override
  public BinaryOperator<Number> getOperator() {
    return (number1, number2) -> new BigDecimal(number1.toString())
        .add(new BigDecimal(number2.toString()));
  }
}