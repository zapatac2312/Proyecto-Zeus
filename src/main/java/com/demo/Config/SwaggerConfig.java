package com.demo.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(info = @Info(
        title = "⚡Proyecto Zeus⚡",
        version = "1.0",
        description = "⚡ Bienvenidos mortales a \"proyecto Zeus\" ⚡\n" +
                "\"Proyecto Zeus\" es una API SpringBoot hecha para gimnasios que brinda soluciones fáciles y robustas para la gestión de usuarios y entrenadores. Esta API fue construida con maven  usando dependencias como: MapStruck para el mapeo eficiente de dato, MySQL driver para guardar la información de todos los usuarios en  una base de datos relacional  con MySQL,  WebFlux para consumir una segunda API que guarda los informes en una base de datos NoSQL MongoDB, entre otras.\n" +
                "\n" +
                "⚡ Welcome mortals to \"project Zeus\" ⚡ \"Project Zeus\" is a SpringBoot API made for gyms that provides easy and robust solutions for managing users and trainers. This API was built with maven using dependencies such as: MapStruck for efficient data mapping, MySQL driver to save all users' information in a relational database with MySQL, WebFlux to consume a second API that saves reports in a NoSQL database MongoDB, among others."+
                "\n" +"\n" +
                "Developers: Jair Daniel Calle Sinitave, Santiago Zapata Correa, Juan David Villamizar Sanchez",
        contact =  @Contact(
                        email = "zapatac2312@gmail.com",
                        url = "https://github.com/zapatac2312/GestionGimnasio")))
public class SwaggerConfig {
}
