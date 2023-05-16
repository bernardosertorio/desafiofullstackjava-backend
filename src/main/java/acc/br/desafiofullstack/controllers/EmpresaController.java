package acc.br.desafiofullstack.controllers;

import java.util.List;

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

import acc.br.desafiofullstack.model.Empresa;
import acc.br.desafiofullstack.services.EmpresaService;

@RestController
@RequestMapping("empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/create")
    public ResponseEntity createEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa empresaCreated = empresaService.createEmpresa(empresa);
            return ResponseEntity.ok().body(empresaCreated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity allEmpresas() {
        try {
            List<Empresa> empresas = empresaService.allEmpresas();
            return ResponseEntity.ok().body(empresas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmpresa(@RequestBody Empresa empresa, @PathVariable(value = "id") long id) {
        try {
            Empresa empresaUpdated = empresaService.updateEmpresa(empresa, id);
            return ResponseEntity.ok().body(empresaUpdated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getEmpresa(@PathVariable(value = "id") long id) {
        try {
            String empresa = empresaService.getEmpresa(id);
            return ResponseEntity.ok().body(empresa);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmpresa(@PathVariable(value = "id") long id) {
        try {
            empresaService.deleteEmpresa(id);
            return ResponseEntity.ok().body("Empresa" + " " + id + " " + "deletada!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
