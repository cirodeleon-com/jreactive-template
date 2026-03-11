// JS Co-localizado para el efecto 3D de la tarjeta
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