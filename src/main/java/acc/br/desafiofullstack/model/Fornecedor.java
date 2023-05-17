package acc.br.desafiofullstack.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj_cpf;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false, length = 8)
    private String cep;
    
    @ManyToMany(mappedBy = "fornecedores")
    private Set<Empresa> empresas;
    
    @JsonIgnore
    @OneToOne(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private FornecedorPessoaFisica pessoaFisica;

    public Fornecedor() {}

    public Fornecedor(String cnpj_cpf, String nome, String email, String cep, Set<Empresa> empresas,
            FornecedorPessoaFisica pessoaFisica) {
        this.cnpj_cpf = cnpj_cpf;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
        this.empresas = empresas;
        this.pessoaFisica = pessoaFisica;
    }

    public String getCnpjCpf() {
        return cnpj_cpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpj_cpf = cnpjCpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Set<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(Set<Empresa> empresas) {
        this.empresas = empresas;
    }

    public FornecedorPessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(FornecedorPessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    @Override
    public String toString() {
        return "Fornecedor [cnpjCpf=" + cnpj_cpf + ", nome=" + nome + ", email=" + email + ", cep=" + cep + ", empresas="
                + empresas + ", pessoaFisica=" + pessoaFisica + "]";
    }
    
}
