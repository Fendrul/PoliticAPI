package be.techni.PoliticAPI.models.dto;

import be.techni.PoliticAPI.models.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClientDTO {
    private Long id;
    private String name;

    public static ClientDTO fromEntity(User entity) {
        if (entity == null) {
            return null;
        }

        return ClientDTO.builder()
                .id(entity.getUser_id())
                .name(entity.getUsername())
                .build();
    }
}
