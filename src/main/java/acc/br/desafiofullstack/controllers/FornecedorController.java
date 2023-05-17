package acc.br.desafiofullstack.controllers;

import java.util.List;

import org.apache.el.stream.Optional;
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
import acc.br.desafiofullstack.services.FornecedorService;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("/create")
    public ResponseEntity createFornecedor(@RequestBody Fornecedor fornecedor) {
        try {
            Fornecedor fornecedorCreated = fornecedorService.createFornecedor(fornecedor);
            return ResponseEntity.ok().body(fornecedorCreated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity allFornecedores() {
        try {
            List<Fornecedor> fornecedores = fornecedorService.allfornecedores();
            return ResponseEntity.ok().body(fornecedores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFornecedor(@RequestBody Fornecedor fornecedor, @PathVariable(value = "id") long id) {
        try {
            Fornecedor fornecedorUpdated = fornecedorService.updateFornecedor(fornecedor, id);
            return ResponseEntity.ok().body(fornecedorUpdated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getFornecedor(@PathVariable(value = "id") long id) {
        try {
            Fornecedor fornecedor = fornecedorService.getFornecedor(id);
            return ResponseEntity.ok().body(fornecedor);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFornecedor(@PathVariable(value = "id") long id) {
        try {
            fornecedorService.deleteFornecedor(id);
            return ResponseEntity.ok().body("Fornecedor" + " " + id + " " + "deletado!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getpf/{id}")
    public ResponseEntity allFornecedorespf(@PathVariable(value = "id") long id) {
        try {
            java.util.Optional<FornecedorPessoaFisica> fornecedores = fornecedorService.getFornecedorpf(id);
            return ResponseEntity.ok().body(fornecedores);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
