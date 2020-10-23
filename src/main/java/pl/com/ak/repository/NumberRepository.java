package pl.com.ak.repository;

import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.ak.enity.Number;

public interface NumberRepository extends JpaRepository<Number, Long> {

  Page<Number> findAllByValueBetween(BigDecimal min, BigDecimal max, Pageable pageable);
}