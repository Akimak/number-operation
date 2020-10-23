package pl.com.ak.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.com.ak.dto.ParameterDto;
import pl.com.ak.service.NumberService;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NumberController {

  private final NumberService numberService;

  @GetMapping("/numbers")
  public Object getUsers(
      @RequestParam(defaultValue = "${app.param.defaultCount}") final Integer count,
      @RequestParam(defaultValue = "${app.param.defaultMin}") final Integer min,
      @RequestParam(defaultValue = "${app.param.defaultMax}") final Integer max) {

    return numberService.run(ParameterDto.builder().count(count).min(min).max(max).build());
  }

}