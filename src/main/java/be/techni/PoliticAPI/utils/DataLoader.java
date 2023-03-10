package be.techni.PoliticAPI.utils;

import be.techni.PoliticAPI.models.entities.Argument;
import be.techni.PoliticAPI.models.entities.Client;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.repositories.CategoryRepository;
import be.techni.PoliticAPI.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {
    private final ArgumentRepository argumentRepo;
    private final ClientRepository clientRepo;
    private final CategoryRepository categoryRepo;

    @Autowired
    public DataLoader(ArgumentRepository argumentRepo, ClientRepository clientRepo, CategoryRepository categoryRepo) {
        this.argumentRepo = argumentRepo;
        this.clientRepo = clientRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Client client = new Client();
        client.setName("test");
        clientRepo.save(client);

        Argument argument = new Argument();
        argument.setAuthor(client);
        argument.setTitle("title test");
        argument.setDescription("description test");
        argumentRepo.save(argument);

        argument = new Argument();
        argument.setAuthor(client);
        argument.setTitle("title test answer");
        argument.setDescription("description test answer");
        argument.setAnswerTo(argumentRepo.findById(1L).get());
        argumentRepo.save(argument);
    }
}
