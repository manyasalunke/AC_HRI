package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {

    private Integer Id;

    @NotBlank(message = "Role name is required")
    @Size(min = 3,message = "Min size of Role Name is 3")
    private String name;
}
