package acc.br.desafiofullstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import acc.br.desafiofullstack.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Query("SELECT f FROM Fornecedor f WHERE f.cnpj_cpf = :documento")
    Optional<Fornecedor> findByCnpjOrCpf(@Param("documento") String documento);
}