package acc.br.desafiofullstack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "fornecedor_pessoa_fisica")
public class FornecedorPessoaFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    
    @Column(nullable = false)
    private String rg;
    
    @Column(nullable = false)
    private LocalDate dataNascimento;

    public FornecedorPessoaFisica() {}

    public FornecedorPessoaFisica(Fornecedor fornecedor, String rg, LocalDate dataNascimento) {
        this.fornecedor = fornecedor;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "FornecedorPessoaFisica [fornecedor=" + fornecedor + ", rg=" + rg + ", dataNascimento=" + dataNascimento
                + "]";
    }
  
}
