package ru.smn.fantasyteam.integration;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.smn.fantasyteam.container.PostgresContainerTest;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class PlayerAdminControllerTest extends PostgresContainerTest {

    private MockMvc mvc;
}