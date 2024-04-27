package com.demo.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(info = @Info(
        title = "⚡Proyecto Zeus⚡",
        version = "1.0",
        description = "Proyecto Zeus es una API hecha con SpringBoot hecha para gimnasios que brinda soluciones fáciles y robustas para la gestión de usuarios ⚡.\n" +
                "\n" +
                "Project Zeus is an API made with SpringBoot made for gyms that provides easy and robust solutions for user management ⚡.",
        contact =  @Contact(
                        name = "Jair Daniel Calle Sinitave, Santiago Zapata Correa, Juan David Villamizar Sanchez",
                        email = "zapatac2312@gmail.com",
                        url = "https://github.com/zapatac2312/GestionGimnasio")))
public class SwaggerConfig {
}
