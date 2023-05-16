package acc.br.desafiofullstack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;
    
    @Column(nullable = false)
    private String nomeFantasia;
    
    @Column(nullable = false, length = 8)
    private String cep;
    
    @ManyToMany
    @JoinTable(
        name = "empresa_fornecedor",
        joinColumns = @JoinColumn(name = "empresa_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    )
    private Set<Fornecedor> fornecedores;

    public Empresa() {}
    
    public Empresa(String cnpj, String nomeFantasia, String cep, Set<Fornecedor> fornecedores) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
        this.fornecedores = fornecedores;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Set<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    @Override
    public String toString() {
        return "Empresa [cnpj=" + cnpj + ", nomeFantasia=" + nomeFantasia + ", cep=" + cep + ", fornecedores="
                + fornecedores + "]";
    }

    
}