package org.serratec.projetoFinal.controller;

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

    @PostMapping("/{idCliente}/{idProduto}")
    public ResponseEntity<String> adicionar(@PathVariable Long idCliente, @PathVariable Long idProduto) {
        return ResponseEntity.ok(favoritoService.adicionarFavorito(idCliente, idProduto));
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<FavoritoDTO>> listar(@PathVariable Long idCliente) {
        return ResponseEntity.ok(favoritoService.listarFavoritos(idCliente));
    }

    @DeleteMapping("/{idCliente}/{idProduto}")
    public ResponseEntity<String> remover(@PathVariable Long idCliente, @PathVariable Long idProduto) {
        return ResponseEntity.ok(favoritoService.removerFavorito(idCliente, idProduto));
    }
}

