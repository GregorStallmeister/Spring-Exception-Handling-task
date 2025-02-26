package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringExceptionHandlingTaskApplicationIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    void getAnimalSpeciesHorse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/horse"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "message": "Only 'dog' is allowed"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void getAninamlSpeciesCat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(MockMvcResultMatchers.status().isIAmATeapot())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "message": "General server error. Please contact the admin."
                        }
                        """))
                .andExpect(jsonPath("$.timestamp").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void getCarsBrandOpel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/opel"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "message": "Only 'porsche' is allowed"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void getAllAnimals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "message": "No element(s) found!"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void getAllCars() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "message": "No element(s) found!"
                        }
                        """));
    }
}
