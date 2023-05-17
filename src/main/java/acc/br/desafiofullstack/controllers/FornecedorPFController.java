package acc.br.desafiofullstack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acc.br.desafiofullstack.model.Fornecedor;
import acc.br.desafiofullstack.model.FornecedorPessoaFisica;
import acc.br.desafiofullstack.services.FornecedorPessoaFisicaService;

@RestController
@RequestMapping("fornecedorpf")
public class FornecedorPFController {

    @Autowired
    private FornecedorPessoaFisicaService fornecedorPFService;

    @PostMapping("/create/{id}")
    public ResponseEntity createFornecedorPF(@RequestBody FornecedorPessoaFisica fornecedorPF, @PathVariable(value = "id") long id) {
        try {
            Optional<Fornecedor> fornecedorPFCreated = fornecedorPFService.createFornecedorPF(fornecedorPF, id);
            return ResponseEntity.ok().body(fornecedorPFCreated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity allFornecedoresPF() {
        try {
            List<FornecedorPessoaFisica> fornecedoresPF = fornecedorPFService.allfornecedoresPF();
            return ResponseEntity.ok().body(fornecedoresPF);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFornecedorPF(@RequestBody FornecedorPessoaFisica fornecedorpf, @PathVariable(value = "id") long id) {
        try {
            FornecedorPessoaFisica fornecedorPFUpdated = fornecedorPFService.updateFornecedorPF(fornecedorpf, id);
            return ResponseEntity.ok().body(fornecedorPFUpdated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getFornecedorPF(@PathVariable(value = "id") long id) {
        try {
            Optional<FornecedorPessoaFisica> fornecedorPF = fornecedorPFService.getFornecedorPF(id);
            return ResponseEntity.ok().body(fornecedorPF);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFornecedorPF(@PathVariable(value = "id") long id) {
        try {
            fornecedorPFService.deleteFornecedorPF(id);
            return ResponseEntity.ok().body("Fornecedor PF" + " " + id + " " + "deletado!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
