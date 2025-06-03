package org.serratec.projetoFinal.controller;

import java.security.Principal;
import java.util.List;

import org.serratec.projetoFinal.dto.FavoritoDTO;
import org.serratec.projetoFinal.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;
    
    @GetMapping
    public ResponseEntity<List<FavoritoDTO>> listar(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(favoritoService.listarFavoritos(email));
    }

    @PostMapping("/{idProduto}")
    public ResponseEntity<String> adicionar(@PathVariable Long idProduto, Principal principal) {
        String email = principal.getName(); // pgar o email do usuario
        return ResponseEntity.ok(favoritoService.adicionarFavorito(email, idProduto));
    }


    @DeleteMapping("/{idProduto}")
    public ResponseEntity<String> remover(@PathVariable Long idProduto, Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(favoritoService.removerFavorito(email, idProduto));
    }
}
