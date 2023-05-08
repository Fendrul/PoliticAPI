package be.techni.PoliticAPI;

import be.techni.PoliticAPI.models.forms.ArgumentForm;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.services.impl.ArgumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PoliticApiApplicationTests {

    @Autowired
    private ArgumentService argumentService;

    @Autowired
    private ArgumentRepository argumentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testSiAjoutArgumentValide() {
        ArgumentForm form = new ArgumentForm();
        form.setTitle("test title");
        form.setDescription("test description");
        form.setClientId(1L);

        long resultCountBefore = argumentRepository.count();

        argumentService.addArgument(form);

        long resultCountAfter = argumentRepository.count();

        assertEquals(resultCountBefore + 1, resultCountAfter);
    }

}
