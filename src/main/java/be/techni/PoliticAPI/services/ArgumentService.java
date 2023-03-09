package be.techni.PoliticAPI.services;

import be.techni.PoliticAPI.models.dto.ArgumentDTO;
import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArgumentService {

    private final ArgumentRepository argumentRepository;

    @Autowired
    public ArgumentService(ArgumentRepository argumentRepository) {
        this.argumentRepository = argumentRepository;
    }

    public List<ArgumentDTO> getListLastArguments(int listLength) {
        List<Argument> lastArguments = argumentRepository.findLastArguments(listLength);

        return lastArguments.stream()
                .map(ArgumentDTO::fromEntity)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
