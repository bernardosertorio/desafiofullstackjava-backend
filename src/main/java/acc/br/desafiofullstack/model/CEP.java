package acc.br.desafiofullstack.model;

public class CEP {
    private String codigo;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public CEP(String codigo, String logradouro, String bairro, String cidade, String estado) {
        this.codigo = codigo;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CEP: " + codigo + "\n" +
                "Logradouro: " + logradouro + "\n" +
                "Bairro: " + bairro + "\n" +
                "Cidade: " + cidade + "\n" +
                "Estado: " + estado;
    }
}
