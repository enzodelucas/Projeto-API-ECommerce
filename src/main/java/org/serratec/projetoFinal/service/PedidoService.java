package org.serratec.projetoFinal.service;

import org.serratec.projetoFinal.repository.ClienteRepository;
import org.serratec.projetoFinal.repository.PedidoRepository;
import org.serratec.projetoFinal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
//	public PedidoDTO
}
