package org.serratec.projetoFinal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.projetoFinal.domain.Cliente;
import org.serratec.projetoFinal.domain.Favorito;
import org.serratec.projetoFinal.domain.Produto;
import org.serratec.projetoFinal.dto.FavoritoDTO;
import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.FavoritoRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoritoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    public String adicionarFavorito(String emailCliente, Long idProduto) {
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

        if (cliente == null) {
            return "Cliente não foi encontrado";
        }

        if (!produtoOpt.isPresent()) {
            return "Produto não foi encontrado";
        }

        Produto produto = produtoOpt.get();

        if (favoritoRepository.existsByClienteAndProdutoId(cliente, idProduto)) {
            return "Produto já está nos seu favoritos";
        }

        favoritoRepository.save(new Favorito(cliente, produto));
        return "Produto adicionado aos favoritos com sucesso";
    }

    public List<FavoritoDTO> listarFavoritos(String emailCliente) {
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        List<FavoritoDTO> lista = new ArrayList<>();

        if (cliente != null) {
            List<Favorito> favoritos = favoritoRepository.findByCliente(cliente);
            for (Favorito f : favoritos) {
                FavoritoDTO dto = new FavoritoDTO(f.getProduto().getId(), f.getProduto().getNome());
                lista.add(dto);
            }
        }

        return lista;
    }

    public String removerFavorito(String emailCliente, Long idProduto) {
        Cliente cliente = clienteRepository.findByEmail(emailCliente);
        if (cliente == null) {
            return "Cliente não foi encontrado";
        }

        favoritoRepository.deleteByClienteAndProdutoId(cliente, idProduto);
        return "Produto removido dos seus favoritos";
    }
}

