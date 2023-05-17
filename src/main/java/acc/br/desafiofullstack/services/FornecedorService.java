package acc.br.desafiofullstack.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import acc.br.desafiofullstack.model.Fornecedor;
import acc.br.desafiofullstack.model.FornecedorPessoaFisica;
import acc.br.desafiofullstack.repository.FornecedorRepository;
import acc.br.desafiofullstack.utils.ValidadorCampos;

import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    ValidadorCampos validadorCampos = new ValidadorCampos();

    public Fornecedor createFornecedor(Fornecedor fornecedor) throws Exception {
        try {
            String cnpjCpf = fornecedor.getCnpjCpf();
            String cep = fornecedor.getCep();
            String email = fornecedor.getEmail();

            validadorCampos.validateCPF(cnpjCpf);
            validadorCampos.validateEmail(email);
            validadorCampos.validateCEP(cep);

            Optional<Fornecedor> fornecedorExist = fornecedorRepository.findByCnpjOrCpf(cnpjCpf);

            if (fornecedorExist.isPresent()) {
                throw new Exception("Fornecedor com CNPJ ou CPF já existente!");
            }

            fornecedorRepository.save(fornecedor);

            return fornecedor;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Fornecedor> allfornecedores() throws Exception {
        try {
            List<Fornecedor> allfornecedores = fornecedorRepository.findAll();

            if (allfornecedores.size() == 0) {
                throw new Exception("Nenhum fornecedor encontrado!");
            }

            return allfornecedores;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Fornecedor updateFornecedor(Fornecedor fornecedor, long id) throws Exception {
        try {
            String cnpjCpf = fornecedor.getCnpjCpf();
            String cep = fornecedor.getCep();
            String email = fornecedor.getEmail();

            validadorCampos.validateCPF(cnpjCpf);
            validadorCampos.validateCNPJ(cnpjCpf);
            validadorCampos.validateEmail(email);
            validadorCampos.validateCEP(cep);
            Optional<Fornecedor> fornecedorFounded = fornecedorRepository.findById(id);

            if (!fornecedorFounded.isPresent()) {
                throw new Exception("Fornecedor não encontrado!");
            }

            Fornecedor fornecedorUpdated = fornecedorFounded.get();

            fornecedorUpdated.setCep(fornecedor.getCep());
            fornecedorUpdated.setCnpjCpf(fornecedor.getCnpjCpf());
            fornecedorUpdated.setEmail(fornecedor.getEmail());
            fornecedorUpdated.setEmpresas(fornecedor.getEmpresas());
            fornecedorUpdated.setNome(fornecedor.getNome());
            fornecedorUpdated.setPessoaFisica(fornecedor.getPessoaFisica());

            fornecedorRepository.save(fornecedorUpdated);

            return fornecedorUpdated;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public Fornecedor getFornecedor(long id) throws Exception {
        try {
            Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
            if (!fornecedor.isPresent()) {
                throw new Exception("Fornecedor não encontrado!");
            }
            return fornecedor.get();
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String deleteFornecedor(long id) throws Exception {
        try {
            Optional<Fornecedor> fornecedorExist = fornecedorRepository.findById(id);

            if (!fornecedorExist.isPresent()) {
                throw new Exception("Fornecedor não encontrado!");
            }

            fornecedorRepository.deleteById(id);
            return "Fornecedor" + id + "deletado.";
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Optional<FornecedorPessoaFisica> getFornecedorpf(long id) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorespf = fornecedorRepository.findByIdFornecedorPF(id);

            if (!fornecedorespf.isPresent()) {
                throw new Exception("Nenhum fornecedor PF encontrado para esse fornecedor!");
            }

            return fornecedorespf;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
