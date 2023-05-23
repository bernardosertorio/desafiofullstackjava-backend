package acc.br.desafiofullstack.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import acc.br.desafiofullstack.model.Empresa;
import acc.br.desafiofullstack.repository.EmpresaRepository;
import acc.br.desafiofullstack.utils.ValidadorCampos;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;
    
    ValidadorCampos validadorCampos = new ValidadorCampos();

    public Empresa createEmpresa(Empresa empresa) throws Exception {
        try {
            String cnpj = empresa.getCnpj();
            String nomeFantasia = empresa.getNomeFantasia();
            String cep = empresa.getCep();

            String cnpjValidate = validadorCampos.validateCNPJ(cnpj);
            empresa.setCnpj(cnpjValidate);

            Optional<Empresa> empresaExist = empresaRepository.findByCnpj(cnpj);

            if (empresaExist.isPresent()) {
                throw new Exception("Empresa com CNPJ já existente!");
            }

            validadorCampos.validateNomeFantasia(nomeFantasia);
            validadorCampos.validateCEP(cep);

            String cepValidated = validadorCampos.removeCaEsCEP(cep);

            empresa.setCep(cepValidated);

            empresaRepository.save(empresa);

            return empresa;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Empresa> allEmpresas() throws Exception {
        try {
            List<Empresa> allEmpresas = empresaRepository.findAll();

            if (allEmpresas.size() == 0) {
                throw new Exception("Nenhuma empresa encontrada!");
            }

            return allEmpresas;
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public Empresa updateEmpresa(Empresa empresa, long id) throws Exception {

        try {
            Optional<Empresa> empresaFounded = empresaRepository.findById(id);

            if (!empresaFounded.isPresent()) {
                throw new Exception("Empresa não encontrada!");
            }

            Empresa empresaUpdated = empresaFounded.get();

            empresaUpdated.setNomeFantasia(empresa.getNomeFantasia());;
            empresaUpdated.setCnpj(empresa.getCnpj());
            empresaUpdated.setCep(empresa.getCep());
            empresaUpdated.setFornecedores(empresa.getFornecedores());

            empresaRepository.save(empresaUpdated);

            return empresaUpdated;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public Empresa getEmpresa(long id) throws Exception {
        try {
            Optional<Empresa> empresa = empresaRepository.findById(id);
            if (!empresa.isPresent()) {
                throw new Exception("Empresa não encontrada!");
            }
            return empresa.get();
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String deleteEmpresa(String cnpj) throws Exception {
        try {

            Optional<Empresa> empresaExist = empresaRepository.findByCnpj(cnpj);

            if (!empresaExist.isPresent()) {
                throw new Exception("Empresa não encontrada!");
            }
            
            empresaRepository.deleteByCnpj(cnpj);
            return "Empresa" + cnpj + "deletada.";
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
