package br.com.ifsp.caixa_eletronico;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CaixaEletronicoApplication {

	//sempre que a aplicação for executada essa config é aplicada
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true); //-> não considera valores nulos no modelmapper
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(CaixaEletronicoApplication.class, args);
	}

}
