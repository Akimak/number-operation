package pl.com.ak.enity;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Builder
public class Number {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;
  private BigDecimal value;
}
