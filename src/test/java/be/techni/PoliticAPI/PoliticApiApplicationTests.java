package be.techni.PoliticAPI;

import be.techni.PoliticAPI.models.forms.ArgumentForm;
import be.techni.PoliticAPI.repositories.ArgumentRepository;
import be.techni.PoliticAPI.services.impl.ArgumentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PoliticApiApplicationTests {

    @Autowired
    private ArgumentService argumentService;

    @Autowired
    private ArgumentRepository argumentRepository;
    @MockBean
    private BindingResult result;

    @BeforeAll
    static void init() {
    }

    @Test
    void testSiAjoutArgumentValide() {
        ArgumentForm form = new ArgumentForm();
        form.setTitle("test title module");
        form.setDescription("test description");
        form.setCategoriesId(new ArrayList<>(Collections.singletonList(1L)));
        form.getCategoriesId().add(4L);

        long resultCountBefore = argumentRepository.count();

        argumentService.addArgument(form, result, "admin");

        long resultCountAfter = argumentRepository.count();

        assertEquals(resultCountBefore + 1, resultCountAfter, "Argument not added");
    }

}
