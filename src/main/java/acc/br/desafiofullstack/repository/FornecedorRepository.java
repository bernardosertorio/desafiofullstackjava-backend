package acc.br.desafiofullstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import acc.br.desafiofullstack.model.Fornecedor;
import acc.br.desafiofullstack.model.FornecedorPessoaFisica;
import jakarta.transaction.Transactional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Query("SELECT f FROM Fornecedor f WHERE f.cnpj_cpf = :documento")
    Optional<Fornecedor> findByCnpjOrCpf(@Param("documento") String documento);

    @Transactional
    @Modifying
    @Query("DELETE FROM Fornecedor f WHERE f.cnpj_cpf = :documento")
    void deleteByCnpjOrCpf(@Param("documento") String documento);

    @Query("SELECT fpf FROM FornecedorPessoaFisica fpf JOIN fpf.fornecedor f WHERE f.id = :id")
    Optional<FornecedorPessoaFisica> findByIdFornecedorPF(@Param("id") long id);    
}