package br.com.juan.spring.data.service;

import br.com.juan.spring.data.orm.Funcionario;
import br.com.juan.spring.data.orm.UnidadeTrabalho;
import br.com.juan.spring.data.repository.FuncionarioRepository;
import br.com.juan.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private boolean system = true;

    public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository, FuncionarioRepository funcionarioRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
        this.funcionarioRepository = funcionarioRepository;
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
                    visualizar();
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
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        System.out.println("Descricao");
        unidadeTrabalho.setDescricao(scanner.next());
        System.out.println("Endereco");
        unidadeTrabalho.setEndereco(scanner.next());
        unidadeTrabalho.setFuncionarios(setFuncionario(scanner));
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner){
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
        System.out.println("Id");
        unidadeTrabalho.setId(scanner.nextInt());
        System.out.println("Descricao");
        unidadeTrabalho.setDescricao(scanner.next());
        System.out.println("Endereco");
        unidadeTrabalho.setEndereco(scanner.next());
        unidadeTrabalho.setFuncionarios(setFuncionario(scanner));
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("atualizado");
    }

    private void visualizar(){
        Iterable<UnidadeTrabalho> unidadeTrabalhos =unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(unidadeTrabalho -> System.out.println(unidadeTrabalho));
    }

    private void deletar(Scanner scanner){
        System.out.println("Id");
        int id = scanner.nextInt();
        unidadeTrabalhoRepository.deleteById(id);
        System.out.println("deletado");
    }

    private List<Funcionario> setFuncionario(Scanner scanner){
        boolean adicionando = true;
        List<Funcionario> funcionarios = new ArrayList<>();

        while (adicionando){
            System.out.println("Digite o funcionarioID (para sair digite 0)");
            Integer funcionarioId = scanner.nextInt();

            if (funcionarioId!=0){
                Optional<Funcionario> funcionario = funcionarioRepository.findById(funcionarioId);
                funcionarios.add(funcionario.get());
            }else{
                adicionando = false;
            }
        }
        return funcionarios;
    }
}
