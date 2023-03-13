package be.techni.PoliticAPI.models.dto;

import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.models.entities.Category;
import be.techni.PoliticAPI.models.entities.Source;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ArgumentDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> source;
    private Map<Long, String> category;
    private Entry<Long, String> author;
    private String date;

    public static ArgumentDTO fromEntity(Argument entity) {
        if (entity == null) {
            return null;
        }

        return ArgumentDTO.builder()
                .id(entity.getArgument_id())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .source(entity.getSources().stream().map(Source::getDescription).toList())
                .category(entity.getCategories().stream()
                        .collect(
                                Collectors.toMap(Category::getCategory_id, Category::getName)
                        )
                )
                .author(Map.entry(entity.getAuthor().getUser_id(), entity.getAuthor().getUsername()))
                .date(entity.getDate().toString())
                .build();
    }
}
