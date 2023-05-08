package be.techni.PoliticAPI.controllers;

import be.techni.PoliticAPI.models.dto.UserDTO;
import be.techni.PoliticAPI.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class UserController {

    private final UserService clientServ;

    @Autowired
    public UserController(UserService clientServ) {
        this.clientServ = clientServ;
    }

    @GetMapping("/id:{id}")
    public UserDTO getClientById(long id) {
        return clientServ.getById(id);
    }

    @GetMapping("/name:{name}")
    public UserDTO getClientByName(@PathVariable("name") String name) {
        return clientServ.getByName(name);
    }
}
