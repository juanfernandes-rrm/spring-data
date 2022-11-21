package br.com.juan.spring.data;

import br.com.juan.spring.data.service.CrudCargoService;
import br.com.juan.spring.data.service.CrudFuncionarioService;
import br.com.juan.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.juan.spring.data.service.RelatoriosService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private CrudCargoService crudCargoService;
	private CrudFuncionarioService crudFuncionarioService;
	private CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private RelatoriosService relatoriosService;
	private boolean system = true;

	public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatoriosService relatoriosService){
		this.crudCargoService = crudCargoService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scannner = new Scanner(System.in);

		while(system){
			System.out.println("Acao para executar");
			System.out.println("0 - sair");
			System.out.println("1 - cargo");
			System.out.println("2 - funcionario");
			System.out.println("3 - unidade de trabalho");
			System.out.println("4 - consulta de relatórios");
			int action = scannner.nextInt();
			switch (action) {
				case 1:
					crudCargoService.inicial(scannner);
					break;
				case 2:
					crudFuncionarioService.inicial(scannner);
					break;
				case 3:
					crudUnidadeTrabalhoService.inicial(scannner);
					break;
				case 4:
				relatoriosService.inicial(scannner);
				break;
				default:
					system = false;
			}
		}
	}
}
