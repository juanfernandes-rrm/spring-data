package br.com.juan.spring.data.repository;

import br.com.juan.spring.data.orm.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    
    List<Funcionario> findByNome(String nome);

    //List<Funcionario> findByNomeAndSalarioGreaterThanAndDataContratacao(String nome, Double salario, LocalDate data);

    @Query("SELECT f FROM Funcionario f " +
            "WHERE f.nome= :nome AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, Double salario, LocalDate data);

    @Query(value="SELECT * FROM funcionario f WHERE f.data_contratacao >= :data",
            nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);
}
