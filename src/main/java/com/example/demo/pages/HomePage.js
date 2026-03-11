// Efecto 3D de la tarjeta
window.tiltCard = function(event, element) {
    const rect = element.getBoundingClientRect();
    const x = event.clientX - rect.left; 
    const y = event.clientY - rect.top;  
    
    const centerX = rect.width / 2;
    const centerY = rect.height / 2;
    
    const rotateX = ((y - centerY) / centerY) * -15;
    const rotateY = ((x - centerX) / centerX) * 15;
    
    element.style.transform = `rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
    
    const glow = element.querySelector('.card-glow');
    if (glow) {
        glow.style.transform = `translate(${x - centerX}px, ${y - centerY}px)`;
    }
};

window.resetTilt = function(element) {
    element.style.transform = 'rotateX(0deg) rotateY(0deg)';
    
    const glow = element.querySelector('.card-glow');
    if (glow) {
        glow.style.transform = 'translate(0px, 0px)';
    }
};

// --- EFECTO DE CONFETI (Estilo JReactive Idiomático) ---
window.ConfettiDemo = {
    check: function(el) {
        // Leemos el nivel directamente del DOM que Idiomorph acaba de actualizar
        const level = parseInt(el.dataset.level, 10);
        
        if (level >= 100 && !el._celebrated) {
            console.log("🎉 Hook client:update detectó Nivel 100. ¡Fuego!");
            el._celebrated = true; // Bloqueo atado al ciclo de vida del nodo
            
            if (!window.confetti) {
                const script = document.createElement('script');
                script.src = "https://cdn.jsdelivr.net/npm/canvas-confetti@1.6.0/dist/confetti.browser.min.js";
                script.onload = () => this.fire();
                document.head.appendChild(script);
            } else {
                this.fire();
            }
        } else if (level < 100) {
            el._celebrated = false; // Reset si baja el nivel
        }
    },
    
    fire: function() {
        confetti({
            particleCount: 150,
            spread: 80,
            origin: { y: 0.6 },
            zIndex: 9999,
            colors: ['#11998e', '#38ef7d', '#ffffff'] // Colores JReactive
        });
    }
};