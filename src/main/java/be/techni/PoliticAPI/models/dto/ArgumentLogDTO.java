package be.techni.PoliticAPI.models.dto;

import be.techni.PoliticAPI.models.entities.ArgumentLog;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArgumentLogDTO {
    private Long id;
    private String description;
    private LocalDateTime modificationDate;
    private String username;

    public static ArgumentLogDTO fromEntity(ArgumentLog entity) {
        if (entity == null) {
            return null;
        }

        return ArgumentLogDTO.builder()
                .id(entity.getArgumentLogId())
                .description(entity.getDescription())
                .modificationDate(entity.getModificationDate())
                .username(entity.getUser().getUsername())
                .build();
    }
}
