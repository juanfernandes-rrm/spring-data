package br.com.juan.spring.data.service;

import br.com.juan.spring.data.orm.Funcionario;
import br.com.juan.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean system = true;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação?");
            System.out.println("0 - sair");
            System.out.println("1 - busca funcionário nome");
            System.out.println("2 - busca funcionário nome, salario maior, data contratacao");
            System.out.println("3 - busca funcionário data contratacao");
            int acao = scanner.nextInt();
            switch (acao){
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Qual nome deseja consultar");
        String name = scanner.next();
        List<Funcionario> funcionarios = funcionarioRepository.findByNome(name);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner){
        System.out.println("Qual nome deseja consultar");
        String name = scanner.next();
        System.out.println("Qual o salario deseja consultar");
        Double salario = scanner.nextDouble();
        System.out.println("Qual data deseja consultar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, dateTimeFormatter);

        List<Funcionario> funcionarios = funcionarioRepository.findNomeDataContratacaoSalarioMaior(name,salario,localDate);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual data deseja consultar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, dateTimeFormatter);
        List<Funcionario> funcionarios = funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarios.forEach(System.out::println);
    }
}
