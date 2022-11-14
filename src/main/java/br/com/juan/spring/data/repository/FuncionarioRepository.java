package br.com.juan.spring.data.repository;

import br.com.juan.spring.data.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}
