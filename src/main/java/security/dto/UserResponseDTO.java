package security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

  private Integer id;
  private String name;
  private String email;
  private String token;
  private boolean active;
  private Date crated;
  private Date modified;
  private Date lastLogin;

}
