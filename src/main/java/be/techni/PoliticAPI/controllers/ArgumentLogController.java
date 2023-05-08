package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.services.ArgumentLogService;
import be.techni.PoliticAPI.services.impl.ArgumentLogServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/argument_log")
public class ArgumentLogController {

    private final ArgumentLogService argumentLogServ;

    public ArgumentLogController(ArgumentLogServiceImpl argumentLogServ) {
        this.argumentLogServ = argumentLogServ;
    }
}
