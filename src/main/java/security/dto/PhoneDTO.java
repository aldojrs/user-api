package security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

  @Size(min = 4, max = 50, message = "Number: Debe contener entre 4 y 50 caracteres")
  private String number;
  private Integer cityCode;
  private Integer countryCode;

}
