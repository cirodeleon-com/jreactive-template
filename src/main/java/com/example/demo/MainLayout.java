package com.example.demo;

import com.ciro.jreactive.HtmlComponent;

public class MainLayout extends HtmlComponent {

    @Override
    protected String template() {
        return """
            <!DOCTYPE html>
            <html lang="es">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>JReactive App</title>
                
                <style>
                    :root {
                        --primary: #11998e;
                        --bg-color: #f4f7f6;
                        --text-color: #333;
                    }
                    body { 
                        margin: 0; 
                        padding: 0; 
                        font-family: 'Segoe UI', Roboto, Helvetica, Arial, sans-serif;
                        background-color: var(--bg-color);
                        color: var(--text-color);
                    }
                    /* Navbar elegante */
                    nav {
                        background: #fff;
                        padding: 15px 40px;
                        box-shadow: 0 2px 15px rgba(0,0,0,0.05);
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                        flex-wrap: wrap;
                        gap: 15px;
                    }
                    nav ul {
                        list-style: none; padding: 0; margin: 0;
                        display: flex; gap: 20px; flex-wrap: wrap;
                    }
                    nav a {
                        text-decoration: none; color: #555;
                        font-weight: 500; transition: color 0.2s;
                    }
                    nav a:hover { color: var(--primary); }
                    
                    /* Contenedor principal centrado */
                    main { 
                        max-width: 1100px; 
                        margin: 0 auto; 
                        padding: 40px 20px; 
                    }
                    footer {
                        text-align: center; padding: 30px;
                        color: #888; font-size: 0.9rem;
                    }
                    /* Animación de navegación SPA */
                    .fade-in { animation: fadeIn 0.4s ease-out; }
                    @keyframes fadeIn { from { opacity:0; transform:translateY(10px); } to { opacity:1; transform:translateY(0); } }
                </style>
            </head>
            <body>
                <nav>
                    <ul>
                        <li><strong style="font-size: 1.3rem; color: var(--primary);">⚡ JReactive</strong></li>
                    </ul>
                    <ul>
                        <li><a href="/" data-router>Inicio</a></li>
                        <li><a href="/tutorial/leccion01" data-router>1. Binding</a></li>
                        <li><a href="/tutorial/leccion02" data-router>2. Eventos</a></li>
                        <li><a href="/tutorial/leccion03" data-router>3. Validación</a></li>
                        <li><a href="/tutorial/leccion04" data-router>4. Listas</a></li>
                        <li><a href="/tutorial/leccion05" data-router>5. Opt UI</a></li>
                        <li><a href="/tutorial/leccion06" data-router>6. Stateless</a></li>
                        <li><a href="/tutorial/leccion07" data-router>7. Co-localización</a></li>
                    </ul>
                </nav>

                <main id="app" class="fade-in">
                    <slot />
                </main>

                <footer>
                    <small>Hecho con Java y ❤️</small>
                </footer>

                <script src="/js/idiomorph.min.js"></script>
                <script src="/js/sockjs.min.js"></script>
                <script src="/js/jreactive-runtime.js"></script>
            </body>
            </html>
        """;
    }
}