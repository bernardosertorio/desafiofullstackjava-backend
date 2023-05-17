package acc.br.desafiofullstack.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import acc.br.desafiofullstack.model.Fornecedor;
import acc.br.desafiofullstack.model.FornecedorPessoaFisica;
import acc.br.desafiofullstack.repository.FornecedorPessoaFisicaRepository;
import acc.br.desafiofullstack.repository.FornecedorRepository;
import acc.br.desafiofullstack.utils.ValidadorCampos;
@Service
public class FornecedorPessoaFisicaService {
       
    @Autowired
    FornecedorPessoaFisicaRepository fornecedorPFRepository;
    @Autowired
    FornecedorRepository fornecedorRository;

    ValidadorCampos validadorCampos = new ValidadorCampos();
    
    public Optional<Fornecedor> createFornecedorPF(FornecedorPessoaFisica fornecedorPF, long id) throws Exception {
        try {

            String rg = fornecedorPF.getRg();
            LocalDate dataNascimento = fornecedorPF.getDataNascimento();
           
            validadorCampos.validateRG(rg);
            validadorCampos.validateDataNascimento(dataNascimento);

            Optional<FornecedorPessoaFisica> fornecedorPFRgExist = fornecedorPFRepository.findByRg(rg);
            Optional<FornecedorPessoaFisica> fornecedorPFExistWithFornecedor = fornecedorPFRepository.findByFornecedorId(id);

            if (fornecedorPFRgExist.isPresent()) {
                throw new Exception("Fornecedor PF RG já vinculado a um fornecedor");
            }

            if (fornecedorPFExistWithFornecedor.isPresent()) {
                throw new Exception("Fornecedor já possui RG vinculado!");
            } 

            Optional<Fornecedor> fornecedorExist = fornecedorRository.findById(id);

            if (!fornecedorExist.isPresent()) {
                throw new Exception("Fornecedor não encontrado!");
            }

            fornecedorPF.setFornecedor(fornecedorExist.get());
            fornecedorExist.get().setPessoaFisica(fornecedorPF);
            fornecedorRository.save(fornecedorExist.get());

            return fornecedorExist;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
            throw new Exception(e.getMessage());
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
            throw new Exception(e.getMessage());
        }
    }

    public Optional<FornecedorPessoaFisica> getFornecedorPF(long id) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPF = fornecedorPFRepository.findById(id);
            if (!fornecedorPF.isPresent()) {
                throw new Exception("Fornecedor PF não encontrado!");
            }
            return fornecedorPF;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String deleteFornecedorPF(long id) throws Exception {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPFExist = fornecedorPFRepository.findById(id);

            if (!fornecedorPFExist.isPresent()) {
                throw new Exception("Fornecedor PF não encontrado!");
            }
            fornecedorPFRepository.deleteById(id);

            return "Fornecedor PF" + id + "deletado.";
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
