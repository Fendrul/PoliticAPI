package be.techni.PoliticAPI.services;

import be.techni.PoliticAPI.models.dto.ArgumentLogDTO;

import java.util.List;

public interface ArgumentLogService {
    List<ArgumentLogDTO> getLogsFromArgumentId(long argumentId);

    boolean addArgumentLog(String modification, long argumentId);
}
