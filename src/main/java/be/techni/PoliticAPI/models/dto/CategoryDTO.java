package be.techni.PoliticAPI.models.dto;

import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.models.entities.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private Map<Long, String> argumentsTitle;

    public static CategoryDTO fromEntity(Category entity) {
        if (entity == null) {
            return null;
        }

        return CategoryDTO.builder()
                .id(entity.getCategory_id())
                .name(entity.getName())
                .argumentsTitle(
                        entity.getArguments().stream()
                                .collect(
                                        Collectors.toMap(Argument::getArgument_id, Argument::getTitle)
                                )
                )
                .build();
    }
}
