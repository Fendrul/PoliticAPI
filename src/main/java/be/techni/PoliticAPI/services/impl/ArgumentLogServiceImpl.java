package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.models.dto.ArgumentLogDTO;
import be.techni.PoliticAPI.services.ArgumentLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArgumentLogServiceImpl implements ArgumentLogService {
    @Override
    public List<ArgumentLogDTO> getLogsFromArgumentId(int argumentId) {
        return null;
    }
}
