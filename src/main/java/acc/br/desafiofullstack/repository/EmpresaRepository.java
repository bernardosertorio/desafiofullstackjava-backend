package acc.br.desafiofullstack.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import acc.br.desafiofullstack.model.Empresa;
import jakarta.transaction.Transactional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj")
    Optional<Empresa> findByCnpj(@Param("cnpj") String cnpj);

    @Transactional
    @Modifying
    @Query("DELETE FROM Empresa e WHERE e.cnpj = :cnpj")
    void deleteByCnpj(@Param("cnpj") String cnpj);
}