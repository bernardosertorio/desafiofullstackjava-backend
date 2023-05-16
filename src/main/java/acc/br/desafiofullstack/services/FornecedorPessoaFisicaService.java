package acc.br.desafiofullstack.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import acc.br.desafiofullstack.model.FornecedorPessoaFisica;
import acc.br.desafiofullstack.repository.FornecedorPessoaFisicaRepository;

public class FornecedorPessoaFisicaService {
       
    @Autowired
    FornecedorPessoaFisicaRepository fornecedorPFRepository;

    public FornecedorPessoaFisica createFornecedorPF(FornecedorPessoaFisica fornecedorPF, String rg) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPFExist = fornecedorPFRepository.findByRg(rg);

            if (fornecedorPFExist.isPresent()) {
                throw new Exception("Fornecedor PF CPF já existente!");
            }

            fornecedorPFRepository.save(fornecedorPF);

            return fornecedorPF;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public List<FornecedorPessoaFisica> allfornecedoresPF() throws Exception {
        try {
            List<FornecedorPessoaFisica> allfornecedoresPF = fornecedorPFRepository.findAll();

            if (allfornecedoresPF.size() == 0) {
                throw new Exception("Nenhum fornecedor PF encontrado!");
            }

            return allfornecedoresPF;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public FornecedorPessoaFisica updateFornecedorPF(FornecedorPessoaFisica fornecedorPF, long id) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPFFounded = fornecedorPFRepository.findById(id);

            if (!fornecedorPFFounded.isPresent()) {
                throw new Exception("Fornecedor PF não encontrado!");
            }

            FornecedorPessoaFisica fornecedorPFUpdated = fornecedorPFFounded.get();

            fornecedorPFUpdated.setDataNascimento(fornecedorPF.getDataNascimento());
            fornecedorPFUpdated.setFornecedor(fornecedorPF.getFornecedor());;
            fornecedorPFUpdated.setRg(fornecedorPF.getRg());;

            fornecedorPFRepository.save(fornecedorPFUpdated);

            return fornecedorPFUpdated;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public String getFornecedorPF(long id) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPF = fornecedorPFRepository.findById(id);
            if (!fornecedorPF.isPresent()) {
                throw new Exception("Fornecedor PF não encontrado!");
            }
            return fornecedorPF.get().toString();
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public String deleteFornecedorPF(long id) throws Exception {
        try {
            fornecedorPFRepository.deleteById(id);

            return "Fornecedor PF" + id + "deletado.";
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
