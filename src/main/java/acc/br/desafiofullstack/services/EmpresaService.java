package acc.br.desafiofullstack.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import acc.br.desafiofullstack.model.Empresa;
import acc.br.desafiofullstack.repository.EmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    public Empresa createEmpresa(Empresa empresa) throws Exception {
        try {
            String cnpj = empresa.getCnpj();
            Optional<Empresa> empresaExist = empresaRepository.findByCnpj(cnpj);

            if (empresaExist.isPresent()) {
                throw new Exception("Empresa com CNPJ já existente!");
            }

            empresaRepository.save(empresa);

            return empresa;

        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("O correu um erro inesperado: " + e.getMessage());
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

    public String getEmpresa(long id) throws Exception {
        try {
            Optional<Empresa> empresa = empresaRepository.findById(id);
            if (!empresa.isPresent()) {
                throw new Exception("Empresa não encontrada!");
            }
            return empresa.get().toString();
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    public String deleteEmpresa(long id) throws Exception {
        try {
            empresaRepository.deleteById(id);

            return "Empresa" + id + "deletada.";
        } catch (HibernateException e) {
            throw new Exception("Erro na execução do Hibernate: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new Exception("Erro de acesso aos dados: " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }

    // public String depositar(Long id, BigDecimal deposito)
    //         throws RetornaUmaContacorrenteException, ValidacaoDeTipoException, DepositoErroException {

    //     // valida se é do tipo BigDecimal e se o valor é válido
    //     ContaCorrenteUtils.validarBigDecimal(deposito);
    //     ContaCorrenteUtils.validarDeposito(deposito);

    //     try {
    //         Optional<ContaCorrente> contaCorrente = correnteRepository.findById(id);

    //         if (contaCorrente.isPresent()) {
    //             throw new RetornaUmaContacorrenteException();
    //         }

    //         ContaCorrente contaCorrente1 = contaCorrente.get();
    //         contaCorrente1.setSaldo(deposito);

    //         List<Extrato> extrato = new ArrayList<>();

    //         extrato.add(new Extrato(contaCorrente.get(), "Deposito", deposito));

    //         contaCorrente1.setExtrato(extrato);

    //         extratoRepository.save(contaCorrente1.getExtrato().get(0));
    //         correnteRepository.save(contaCorrente1);

    //         return "Novo valor: " + contaCorrente1.getSaldo();
    //     } catch (HibernateException e) {
    //         throw new HibernateException(e);
    //     }

    // }

    // public String Sacar(Long id, BigDecimal sacar) throws RetornaUmaContacorrenteException, ValidacaoDeTipoException,
    //         SaqueErroException, EstouroSaqueException {

    //     ContaCorrenteUtils.validarBigDecimal(sacar);
    //     ContaCorrenteUtils.validarSaque(sacar);

    //     try {
    //         Optional<ContaCorrente> contaCorrente = correnteRepository.findById(id);

    //         if (contaCorrente.isPresent()) {
    //             throw new RetornaUmaContacorrenteException();
    //         }

    //         BigDecimal saldo = contaCorrente.get().getSaldo().subtract(sacar);

    //         if (saldo.compareTo(saldo) < 0) {
    //             throw new EstouroSaqueException(sacar);
    //         }

    //         ContaCorrente contaCorrente1 = contaCorrente.get();
    //         contaCorrente1.diminuirSaldo(sacar);

    //         List<Extrato> extrato = new ArrayList<>();

    //         extrato.add(new Extrato(contaCorrente.get(), "Saque", sacar));

    //         contaCorrente1.setExtrato(extrato);
    //         extratoRepository.save(contaCorrente1.getExtrato().get(0));
    //         correnteRepository.save(contaCorrente1);

    //         return "Novo valor: " + contaCorrente1.getSaldo();
    //     } catch (HibernateException e) {
    //         throw new HibernateException(e);
    //     }
    // }

    // public String Transferir(Long idA, Long idB, BigDecimal valor) throws EstouroSaqueException,
    //         ValidacaoDeTipoException, SaqueErroException, RetornaDuasContacorrenteException {

    //     ContaCorrenteUtils.validarBigDecimal(valor);
    //     ContaCorrenteUtils.validarSaque(valor);

    //     try {
    //         Optional<ContaCorrente> conta1 = correnteRepository.findById(idA);
    //         Optional<ContaCorrente> conta2 = correnteRepository.findById(idB);

    //         if (conta1.isPresent() && conta2.isPresent()) {
    //             throw new RetornaDuasContacorrenteException();
    //         }

    //         BigDecimal saldo = conta1.get().getSaldo().subtract(valor);

    //         if (saldo.compareTo(saldo) < 0) {
    //             throw new EstouroSaqueException(valor);
    //         }

    //         ContaCorrente conta1N = conta1.get();
    //         ContaCorrente conta2N = conta2.get();

    //         conta1N.diminuirSaldo(valor);
    //         conta2N.setSaldo(valor);

    //         List<Extrato> extrato = new ArrayList<>();

    //         extrato.add(new Extrato(conta1.get(), "Transferido", valor));
    //         extrato.add(new Extrato(conta2.get(), "Recebido", valor));

    //         conta1N.setExtra(new Extrato(conta1.get(), "Transferido", valor));
    //         conta2N.setExtra(new Extrato(conta2.get(), "Recebido", valor));

    //         extratoRepository.save(conta1N.getExtrato().get(0));
    //         extratoRepository.save(conta2N.getExtrato().get(0));

    //         correnteRepository.save(conta1N);
    //         correnteRepository.save(conta2N);

    //         return "Primeira conta, novo valor: " + conta1N.getSaldo() + "\n" + "Segunda Conta, novo valor: "
    //                 + conta2N.getSaldo();
    //     } catch (HibernateException e) {
    //         throw new HibernateException(e);
    //     }

    // }
}
