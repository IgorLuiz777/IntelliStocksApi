package br.com.intellistocks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Controller
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "IntelliStocks API",
        version = "2.0",
        description = "API da aplicação IntelliStocks - Sistema de iventário inteligente",
        contact = @Contact(
            name = "Igor Luiz",
            email = "igorluizpereiralima@gmail.com",
            url = "https://github.com/IgorLuiz777"
        ),
        license = @License(
            name = "Repositório - GitHub",
            url = "https://github.com/IgorLuiz777/IntelliStocksApi"
        )
    )
)
public class IntelliStocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelliStocksApplication.class, args);
	}

	@RequestMapping
	@ResponseBody
	public String projectName() {
		return "IntelliStocks";
	}

}
