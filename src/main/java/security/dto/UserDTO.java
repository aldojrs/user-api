package security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  @Size(min = 4, max = 255, message = "Name: mínimo 4 caracteres")
  private String name;

  @Email(message = "Email: formato inválido")
  private String email;

  @Pattern(regexp = "^(?=(.*\\d){2})(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).{8,16}$", message = "Password: debe tener una longitud entre 8 y 16, debe contener minúsculas, mayúsculas y 2 números")
  private String password;

  private List<PhoneDTO> phones;

}
