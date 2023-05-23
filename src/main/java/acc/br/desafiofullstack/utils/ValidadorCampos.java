package acc.br.desafiofullstack.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import com.google.gson.Gson;

public class ValidadorCampos {

    public boolean isCpf(String value) {
        String CPF_REGEX = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}";
        return value.matches(CPF_REGEX);
    }

    public boolean isCnpj(String value) {
        String CNPJ_REGEX = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}";
        return value.matches(CNPJ_REGEX);
    }

    public String validateCNPJ(String cnpj) throws Exception {
        try {
            cnpj = cnpj.replaceAll("[^\\d]", "");

            if (cnpj.length() != 14) {
                throw new Exception("CNPJ inválido!");
            }

            if (cnpj.matches("(\\d)\\1*")) {
                throw new Exception("CNPJ inválido!");
            }

            // Calcular o primeiro dígito verificador
            int soma = 0;
            int peso = 2;
            for (int i = 11; i >= 0; i--) {
                int digito = cnpj.charAt(i) - '0';
                soma += digito * peso;
                peso = (peso + 1) % 10;
                if (peso == 0) {
                    peso = 2;
                }
            }
            int digito1 = (soma % 11 < 2) ? 0 : (11 - soma % 11);

            // Calcular o segundo dígito verificador
            soma = 0;
            peso = 2;
            for (int i = 12; i >= 0; i--) {
                int digito = cnpj.charAt(i) - '0';
                soma += digito * peso;
                peso = (peso + 1) % 10;
                if (peso == 0) {
                    peso = 2;
                }
            }
            int digito2 = (soma % 11 < 2) ? 0 : (11 - soma % 11);

            // Verificar se os dígitos verificadores estão corretos
            boolean cnpjCorrect = cnpj.charAt(12) - '0' == digito1 && cnpj.charAt(13) - '0' == digito2;

            if (!cnpjCorrect) {
                throw new Exception("CNPJ inválido!");
            }

            return cnpj;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validateCPF(String cpf) throws Exception {
        try {
            cpf = cpf.replaceAll("[^\\d]", "");

            if (cpf.length() != 11) {
                throw new Exception("CPF inválido!");
            }

            if (cpf.matches("(\\d)\\1*")) {
                throw new Exception("CPF inválido!");
            }

            // Calcular o primeiro dígito verificador
            int soma = 0;
            int peso = 10;
            for (int i = 0; i < 9; i++) {
                int digito = cpf.charAt(i) - '0';
                soma += digito * peso;
                peso--;
            }
            int digito1 = (soma % 11 < 2) ? 0 : (11 - soma % 11);

            // Calcular o segundo dígito verificador
            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                int digito = cpf.charAt(i) - '0';
                soma += digito * peso;
                peso--;
            }
            int digito2 = (soma % 11 < 2) ? 0 : (11 - soma % 11);

            // Verificar se os dígitos verificadores estão corretos
            return cpf.charAt(9) - '0' == digito1 && cpf.charAt(10) - '0' == digito2;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validateRG(String rg) throws Exception {
        try {
            rg = rg.replaceAll("[^\\d]", "");

            int tamanhoMinimo = 4;
            int tamanhoMaximo = 9;
            if (rg.length() < tamanhoMinimo || rg.length() > tamanhoMaximo) {
                throw new Exception("RG inválido!");
            }

            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validateDataNascimento(LocalDate dataNascimento) throws Exception {
        try {
            LocalDate dataAtual = LocalDate.now();
            if (dataNascimento == null || dataNascimento.isAfter(dataAtual)) {
                throw new Exception("Data de nascimento inválida!");
            }
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validateNomeFantasia(String nomeFantasia) throws Exception {
        try {
            if (nomeFantasia.length() > 100) {
                throw new Exception("Nome fantasia muito grande!");
            }

            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean validateEmail(String email) throws Exception {
        try {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            if (!email.matches(regex)) {
                throw new Exception("E-mail inválido!");
            }
            return email.matches(regex);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String removeCaEsCEP(String cep) {
        String cepSemCaracteresEspeciais = cep.replaceAll("[^0-9-]", "");
    
        String cepSemHifen = cepSemCaracteresEspeciais.replaceAll("-", "");
    
        return cepSemHifen;
    } 

    public Object validateCEP(String cep) throws Exception {
        try {
            URL url = new URL("http://cep.la/" + cep);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String resultadoCEP = response.toString();

                Gson gson = new Gson();
                Object jsonCep = gson.fromJson(resultadoCEP, Object.class);
                
                return jsonCep;
            } else {
                throw new Exception("CEP não encontrado!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
