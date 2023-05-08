package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.ArgumentLogDTO;
import be.techni.PoliticAPI.services.ArgumentLogService;
import be.techni.PoliticAPI.services.impl.ArgumentLogServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/argument_log")
public class ArgumentLogController {

    private final ArgumentLogService argumentLogServ;

    public ArgumentLogController(ArgumentLogServiceImpl argumentLogServ) {
        this.argumentLogServ = argumentLogServ;
    }

    @GetMapping("logs_from_argument/{id}")
    public List<ArgumentLogDTO> getLogsFromArgument(@PathVariable("id") int argumentId) {
        return argumentLogServ.getLogsFromArgumentId(argumentId);
    }
}
