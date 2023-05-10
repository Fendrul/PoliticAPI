package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.exceptions.RessourceNotFound;
import be.techni.PoliticAPI.models.dto.ArgumentLogDTO;
import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.repositories.ArgumentLogRepository;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.services.ArgumentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArgumentLogServiceImpl implements ArgumentLogService {
    private final ArgumentLogRepository argumentLogRepo;
    private final ArgumentRepository argumentRepo;

    public ArgumentLogServiceImpl(ArgumentLogRepository argumentLogRepo, ArgumentRepository argumentRepo) {
        this.argumentLogRepo = argumentLogRepo;
        this.argumentRepo = argumentRepo;
    }

    @Override
    public List<ArgumentLogDTO> getLogsFromArgumentId(long argumentId) {

        Argument argument = argumentRepo.findById(argumentId)
                .orElseThrow(() -> new RessourceNotFound("Argument by id %d not found".formatted(argumentId)));

        return argumentLogRepo.findAllByArgumentId(argument.getArgument_id())
                .stream().map(ArgumentLogDTO::fromEntity).toList();
    }
}
