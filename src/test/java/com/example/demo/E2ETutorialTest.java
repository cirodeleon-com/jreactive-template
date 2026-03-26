package com.example.demo;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

// Usamos las aserciones nativas de Playwright (auto-retries)
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class E2ETutorialTest {

    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeAll
    static void beforeAll() {
        playwright = Playwright.create();
        // Lanza un Chromium invisible. Es ultrarrápido.
        browser = playwright.chromium().launch(); 
    }

    @AfterAll
    static void afterAll() {
        playwright.close();
    }

    @BeforeEach
    void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    @DisplayName("Lección 01: El Two-Way Binding debe reflejarse en el DOM")
    void testTwoWayBinding() {
        // 1. Entrar a la página
        page.navigate("http://localhost:" + port + "/tutorial/leccion01");

        // 2. Localizadores
        Locator input = page.locator("input[name='saludoState']");
        Locator textoDinamico = page.locator("p strong");

        // 3. Escribir (JReactive enviará los cambios por WebSocket al instante)
        input.fill("Ciro de León");

        // 4. Aserción (Playwright esperará automáticamente hasta que el texto cambie)
        assertThat(textoDinamico).hasText("Ciro de León");
    }

    @Test
    @DisplayName("Lección 05: Optimistic UI debe reaccionar en 0ms y mantener consistencia")
    void testOptimisticUI() {
        // 1. Entrar a la página
        page.navigate("http://localhost:" + port + "/tutorial/leccion05");

        Locator btnLike = page.locator("button[data-optimistic]");
        Locator h2Contador = page.locator("h2");

        // Estado inicial
        assertThat(h2Contador).hasText("Me gusta: 0");
        assertThat(btnLike).hasText("🤍 Dar Like");

        // 2. Hacemos clic (El backend de Java tiene un Thread.sleep(1000))
        btnLike.click();

        // 3. Aserción Inmediata (0ms)
        // El proxy de JS debe haber mutado el DOM instantáneamente gracias a data-optimistic
        assertThat(btnLike).hasText("❤️ Te gusta");
        assertThat(h2Contador).hasText("Me gusta: 1");

        // 4. Aserción Post-Servidor
        // Esperamos 1.5 segundos para que la respuesta real del servidor llegue.
        // El estado debe mantenerse consistente (no debe haber parpadeos o rollbacks).
        page.waitForTimeout(1500);
        assertThat(h2Contador).hasText("Me gusta: 1");
    }
}