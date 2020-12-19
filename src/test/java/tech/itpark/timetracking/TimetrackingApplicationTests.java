package tech.itpark.timetracking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TimetrackingApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void crudTest() throws Exception {
        mockMvc.perform(get("/employee"))
                .andExpect(content()
                        .json("[\n" +
                                "  {\n" +
                                "    \"id\": 1,\n" +
                                "    \"name\": \"Ivan Ivanov\",\n" +
                                "    \"position\": \"Manager\",\n" +
                                "    \"clock_rate\": 162\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 2,\n" +
                                "    \"name\": \"Petr Petrov\",\n" +
                                "    \"position\": \"Manager\",\n" +
                                "    \"clock_rate\": 162\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 3,\n" +
                                "    \"name\": \"Maksim Maksimov\",\n" +
                                "    \"position\": \"Administrator\",\n" +
                                "    \"clock_rate\": 162\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 4,\n" +
                                "    \"name\": \"Elena Nikolaevna\",\n" +
                                "    \"position\": \"Bookkeeper\",\n" +
                                "    \"clock_rate\": 162\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"id\": 5,\n" +
                                "    \"name\": \"Olga Tarasova\",\n" +
                                "    \"position\": \"Human resources\",\n" +
                                "    \"clock_rate\": 162\n" +
                                "  }\n" +
                                "]"));

        mockMvc.perform(get("/employee/1"))
                .andExpect(content()
                        .json("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"name\": \"Ivan Ivanov\",\n" +
                                "  \"position\": \"Manager\",\n" +
                                "  \"clock_rate\": 162\n" +
                                "}"));

        mockMvc.perform(get("/employee/search")
                .queryParam("name", "Maksim Maksimov")
        )
                .andExpect(content()
                        .json(
                                "[\n" +
                                        "  {\n" +
                                        "    \"id\": 3,\n" +
                                        "    \"name\": \"Maksim Maksimov\",\n" +
                                        "    \"position\": \"Administrator\",\n" +
                                        "    \"clock_rate\": 162\n" +
                                        "  }\n" +
                                        "]"));

        mockMvc.perform(post("/employee")
                .contentType("application/json")
                .content("{\n" +
                        "  \"id\":0,\n" +
                        "  \"name\":\"Pet\",\n" +
                        "  \"position\":\"Writer\",\n" +
                        "  \"clock_rate\":160\n" +
                        "}")
        )
                .andExpect(content()
                        .json(
                                "{\n" +
                                        "  \"id\": 6,\n" +
                                        "  \"name\": \"Pet\",\n" +
                                        "  \"position\": \"Writer\",\n" +
                                        "  \"clock_rate\": 160\n" +
                                        "}"));

        mockMvc.perform(post("/employee")
                .contentType("application/json")
                .content("{\n" +
                        "  \"id\":6,\n" +
                        "  \"name\":\"Rick\",\n" +
                        "  \"position\":\"Writer\",\n" +
                        "  \"clock_rate\":160\n" +
                        "}")
        )
                .andExpect(content()
                        .json(
                                "{\n" +
                                        "  \"id\": 6,\n" +
                                        "  \"name\": \"Rick\",\n" +
                                        "  \"position\": \"Writer\",\n" +
                                        "  \"clock_rate\": 160\n" +
                                        "}"));

        mockMvc.perform(delete("/employee/6"))
                .andExpect(content()
                        .json("{\n" +
                                "  \"id\":6,\n" +
                                "  \"name\":\"Rick\",\n" +
                                "  \"position\":\"Writer\",\n" +
                                "  \"clock_rate\":160" +
                                "\n" +
                                "}"));
    }
}
