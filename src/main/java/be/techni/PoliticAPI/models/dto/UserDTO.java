package be.techni.PoliticAPI.models.dto;

import be.techni.PoliticAPI.models.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDTO {
    private Long id;
    private String name;

    public static UserDTO fromEntity(User entity) {
        if (entity == null) {
            return null;
        }

        return UserDTO.builder()
                .id(entity.getUser_id())
                .name(entity.getUsername())
                .build();
    }
}
