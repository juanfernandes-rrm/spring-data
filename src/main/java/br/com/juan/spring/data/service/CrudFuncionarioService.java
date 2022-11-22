package br.com.juan.spring.data.service;

import br.com.juan.spring.data.orm.Cargo;
import br.com.juan.spring.data.orm.Funcionario;
import br.com.juan.spring.data.orm.UnidadeTrabalho;
import br.com.juan.spring.data.repository.CargoRepository;
import br.com.juan.spring.data.repository.FuncionarioRepository;
import br.com.juan.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final CargoRepository cargoRepository;
    private boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner){
        while (system){
            System.out.println("Qual ação?");
            System.out.println("0 - sair");
            System.out.println("1 - salvar");
            System.out.println("2 - atualizar");
            System.out.println("3 - visualizar");
            System.out.println("4 - deletar");
            int acao = scanner.nextInt();
            switch (acao){
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }

    private void salvar(Scanner scanner){
        Funcionario funcionario = new Funcionario();
        System.out.println("Nome do funcionario:");
        funcionario.setNome(scanner.next());
        System.out.println("cpf");
        funcionario.setCpf(scanner.next());
        System.out.println("salário");
        funcionario.setSalario(scanner.nextDouble());
        System.out.println("data de contratacão (dd-MM-yyyy)");
        try {
            funcionario.setDataContratacao(formataData(scanner.next()));
        } catch (ParseException e) {
            throw new RuntimeException("Data em formato errado",e);
        }
        System.out.println("cargo id");
        Optional<Cargo> cargo = cargoRepository.findById(scanner.nextInt());
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(setUnidadesTrabalho(scanner));
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner){
        Funcionario funcionario = new Funcionario();
        System.out.println("Id funcionario");
        funcionario.setId(scanner.nextInt());
        System.out.println("Nome do funcionario:");
        funcionario.setNome(scanner.next());
        System.out.println("cpf");
        funcionario.setCpf(scanner.next());
        System.out.println("salário");
        funcionario.setSalario(scanner.nextDouble());
        System.out.println("data de contratacão (dd-MM-yyyy)");
        try {
            funcionario.setDataContratacao(formataData(scanner.next()));
        } catch (ParseException e) {
            throw new RuntimeException("Data em formato errado",e);
        }
        System.out.println("cargo id");
        Optional<Cargo> cargo = cargoRepository.findById(scanner.nextInt());
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(setUnidadesTrabalho(scanner));
        funcionarioRepository.save(funcionario);
        System.out.println("atualizado");
    }

    private void visualizar(Scanner scanner){
        System.out.println("Qual página você deseja visualizar?");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page,5, Sort.by(Sort.Direction.ASC, "nome"));

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        System.out.println(funcionarios);
        System.out.println("Página atual "+funcionarios.getNumber());
        System.out.println("Total de funcionários "+funcionarios.getTotalElements());
        funcionarios.forEach(funcionario -> System.out.println(funcionario));
    }

    private void deletar(Scanner scanner){
        System.out.println("Id");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("deletado");
    }

    private LocalDate formataData(String data) throws ParseException {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dataFormatada = simpleDateFormat.parse(data);
        return dataFormatada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private List<UnidadeTrabalho> setUnidadesTrabalho(Scanner scanner){
        boolean adicionando = true;
        List<UnidadeTrabalho> unidadeTrabalhos = new ArrayList<>();

        while (adicionando){
            System.out.println("Digite a unidadeId (para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if (unidadeId!=0){
                Optional<UnidadeTrabalho> unidadeTrabalho = unidadeTrabalhoRepository.findById(unidadeId);
                unidadeTrabalhos.add(unidadeTrabalho.get());
            }else{
                adicionando = false;
            }
        }
        return unidadeTrabalhos;
    }
}


