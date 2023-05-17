package acc.br.desafiofullstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import acc.br.desafiofullstack.model.FornecedorPessoaFisica;

@Repository
public interface FornecedorPessoaFisicaRepository extends JpaRepository<FornecedorPessoaFisica, Long> {
    Optional<FornecedorPessoaFisica> findByRg(String rg);
    Optional<FornecedorPessoaFisica> findByFornecedorId(long id);
}
