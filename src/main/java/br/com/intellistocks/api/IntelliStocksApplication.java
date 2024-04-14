package br.com.intellistocks.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
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
