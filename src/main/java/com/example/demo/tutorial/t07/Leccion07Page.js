// Este script se carga automáticamente porque se llama igual que la clase Java.
window.animarCajaMagica = function(elemento) {
    const colores = ['#e83e8c', '#28a745', '#ffc107', '#fd7e14', '#20c997'];
    const colorAleatorio = colores[Math.floor(Math.random() * colores.length)];
    
    // Cambiamos el color de fondo dinámicamente
    elemento.style.background = colorAleatorio;
    
    // Aplicamos una rotación y un escalado divertido
    elemento.style.transform = `rotate(${Math.random() * 10 - 5}deg) scale(1.1)`;
    
    // Restauramos el tamaño después de 300ms
    setTimeout(() => {
        elemento.style.transform = 'rotate(0deg) scale(1)';
    }, 300);
    
    console.log("✨ Animación ejecutada desde Leccion07Page.js");
}