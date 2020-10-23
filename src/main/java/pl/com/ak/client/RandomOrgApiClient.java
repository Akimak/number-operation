package pl.com.ak.client;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${app.random-service.url}", name = "random-service")
public interface RandomOrgApiClient {

  @GetMapping(value = "${app.random-service.service}", consumes = TEXT_PLAIN_VALUE)
  @SuppressWarnings("all")
  String getRandomIntegers(
      @RequestParam int num,
      @RequestParam int min,
      @RequestParam int max,
      @RequestParam int col,
      @RequestParam int base,
      @RequestParam String format,
      @RequestParam String rnd
  );

}