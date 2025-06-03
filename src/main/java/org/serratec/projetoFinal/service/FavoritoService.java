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
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public String adicionarFavorito(Long idCliente, Long idProduto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        Optional<Produto> produtoOpt = produtoRepository.findById(idProduto);

        if (!clienteOpt.isPresent()) return "Cliente não encontrado";
        if (!produtoOpt.isPresent()) return "Produto não encontrado";

        Cliente cliente = clienteOpt.get();
        Produto produto = produtoOpt.get();

        if (favoritoRepository.existsByClienteAndProdutoId(cliente, idProduto)) {
            return "Produto já está nos seus favoritos";
        }

        Favorito favorito = new Favorito(cliente, produto);
        favoritoRepository.save(favorito);

        return "Produto adicionado aos seus favoritos";
    }

    public List<FavoritoDTO> listarFavoritos(Long idCliente) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        List<FavoritoDTO> lista = new ArrayList<>();

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            List<Favorito> favoritos = favoritoRepository.findByCliente(cliente);

            for (Favorito f : favoritos) {
                lista.add(new FavoritoDTO(f.getProduto().getId(), f.getProduto().getNome()));
            }
        }

        return lista;
    }

    public String removerFavorito(Long idCliente, Long idProduto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);
        if (!clienteOpt.isPresent()) return "Cliente não encontrado";

        Cliente cliente = clienteOpt.get();
        favoritoRepository.deleteByClienteAndProdutoId(cliente, idProduto);
        return "Seu produto foi removido dos favoritos";
    }
}