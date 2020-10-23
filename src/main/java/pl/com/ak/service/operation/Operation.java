package pl.com.ak.service.operation;

import java.util.function.BinaryOperator;

public interface Operation {
  BinaryOperator<Number> getOperator();
}
