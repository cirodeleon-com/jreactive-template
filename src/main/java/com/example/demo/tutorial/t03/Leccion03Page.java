package com.example.demo.tutorial.t03;

import com.ciro.jreactive.State;
import java.io.Serializable;
import com.ciro.jreactive.annotations.Call;
import com.ciro.jreactive.router.Route;
import com.example.demo.AppPage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Route(path = "/tutorial/leccion03")
public class Leccion03Page extends AppPage{
	
	public static class SignupDto implements Serializable{

        @NotBlank(message = "El nombre es obligatorio")
        public String name;

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        public String email;

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        public String password;
    }
	
	@State
    SignupDto form = new SignupDto();
	
	@State 
	String mensaje = "";
	
	@Call
    public void register(@Valid SignupDto form) {
        // Si la validación falla, NUNCA se entra aquí
        mensaje = "Usuario " + form.name + " registrado correctamente";
        this.form = new SignupDto();
    }
	
	@Call
    public void limpiar() {
        mensaje = "";
        form = new SignupDto();
    }
	
	// el framework hace la validacion internamente segun 
	// las anotaciones del dto cuando usas @Valid
	@Call
    public void validar(@Valid SignupDto form) {
        // la validacion la hace automaticamente el framework
        // si entra aqui llego valido el dto
    }
	
    @Override
    protected String template() {
        return """
            <section>
              <h1>Registro con Bean Validation</h1>

              <div>
                <label>
                <!-- se valida cada que el usuario hace un evento de teclado 
                     se usa form.name quien recibe el valor del input
                -->
                  Nombre:
                  <input type="text"
                  @input="validar(form)"
                         name="form.name">
                </label>
              </div>

              <div>
                <label>
                <!-- se valida cada que el usuario hace un evento de teclado 
                     se usa form.email quien recibe el valor del input
                -->
                  Correo:
                  <input type="email"
                  @input="validar(form)"
                         name="form.email">
                </label>
              </div>

              <div>
                <label>
                <!-- se valida cada que el usuario hace un evento de teclado 
                     se usa form.password quien recibe el valor del input
                -->
                  Contraseña:
                  <input type="password"
                  @input="validar(form)"
                         name="form.password">
                </label>
              </div>

              <!-- se llama al metodo register al hacer click en el boton
              -->
              <button type="button"
                      @click="register(form)">
                Registrarme
              </button>
              
              <button type="button"
                      @click="limpiar()">
                Limpiar
              </button>

              {{#if mensaje }}
                 <p>{{mensaje}}</p>
              {{/if}}
            </section>
            """;
    }

}
