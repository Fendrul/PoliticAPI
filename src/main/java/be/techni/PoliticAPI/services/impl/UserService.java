package be.techni.PoliticAPI.services.impl;

import be.techni.PoliticAPI.jwt.JwtProvider;
import be.techni.PoliticAPI.models.dto.UserDTO;
import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.repositories.RoleRepository;
import be.techni.PoliticAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider, RoleRepository roleRepo) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.roleRepo = roleRepo;
    }

    public UserDTO getById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return UserDTO.fromEntity(user);
    }

    public UserDTO getByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return UserDTO.fromEntity(user);
    }


}
