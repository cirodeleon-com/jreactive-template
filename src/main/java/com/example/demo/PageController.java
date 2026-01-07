package com.example.demo;

import com.ciro.jreactive.CallGuard;
import com.ciro.jreactive.JrxHttpApi;
import com.ciro.jreactive.PageResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PageController {

    private final PageResolver pageResolver;
    private final JrxHttpApi api;

    // Inyección de dependencias (estos Beans vienen de tu librería jreactive-starter-spring)
    public PageController(PageResolver pageResolver,
                          ObjectMapper objectMapper,
                          CallGuard guard) {
        this.pageResolver = pageResolver;
        // Instanciamos el API helper usando las piezas que nos da el Framework
        this.api = new JrxHttpApi(pageResolver, objectMapper, guard);
    }

    // 1. Manejo de Rutas (SPA Routing)
    // Captura cualquier ruta que no sea un archivo estático (js, css, ws)
    @GetMapping(value = {
            "/",
            "/{x:^(?!js|ws|static).*$}",
            "/{x:^(?!js|ws|static).*$}/**"
    }, produces = MediaType.TEXT_HTML_VALUE)
    public String page(HttpServletRequest req,
                       @RequestHeader(value = "X-Partial", required = false) String partial) {
        
        String path = req.getRequestURI();
        String sessionId = req.getSession(true).getId();

        // Renderizamos el componente correspondiente a la ruta
        String contentHtml = api.render(sessionId, path);

        // Si es una navegación interna (AJAX), devolvemos solo el HTML del componente
        if (partial != null) return contentHtml;

        // Si es la primera carga, devolvemos el HTML completo (El Shell)
        return """
            <!DOCTYPE html>
            <html lang="es">
              <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>JReactive App</title>
                
                <script src="/js/jreactive-runtime.js"></script>
                
                <style>
                    body { margin: 0; font-family: system-ui, -apple-system, sans-serif; background: #f4f4f9; }
                </style>
              </head>
              <body>
                <div id="app">%s</div>
              </body>
            </html>
            """.formatted(contentHtml);
    }

    // 2. Manejo de llamadas RPC (@Call)
    @PostMapping(
            value = "/call/{qualified:.+}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String callMethod(@PathVariable("qualified") String qualified,
                             @RequestBody Map<String, Object> body,
                             HttpServletRequest req) {

        String path = req.getHeader("Referer");
        // Limpieza básica del path (quitar dominio)
        if (path != null) {
            path = path.replaceFirst("https?://[^/]+", "");
            int q = path.indexOf('?');
            if (q != -1) path = path.substring(0, q);
        } else {
            path = "/";
        }

        String sessionId = req.getSession(true).getId();
        
        // Delegamos la ejecución al motor del Framework
        return api.call(sessionId, path, qualified, body);
    }
}