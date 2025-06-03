package org.serratec.projetoFinal.controller;

import java.io.IOException;
import java.net.URI;

import java.util.List;
import java.io.ByteArrayOutputStream;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.serratec.projetoFinal.domain.Pedido;
import org.serratec.projetoFinal.dto.ClienteDTO;
import org.serratec.projetoFinal.dto.FuncionarioDTO;
import org.serratec.projetoFinal.dto.FuncionarioInserirDTO;
import org.serratec.projetoFinal.dto.PedidoAtualizarStatusDTO;
import org.serratec.projetoFinal.dto.PedidoDTO;
import org.serratec.projetoFinal.service.ClienteService;
import org.serratec.projetoFinal.service.FuncionarioService;
import org.serratec.projetoFinal.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ClienteService clienteService;

	@PostMapping("/inserirFuncionario")
	public ResponseEntity<FuncionarioDTO> inserirFuncionario(@Valid @RequestBody FuncionarioInserirDTO funcionarioIns) {
		FuncionarioDTO funcionarioDTO = funcionarioService.inserir(funcionarioIns);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(funcionarioDTO);
	}
	
	@GetMapping("/listarFuncionarios")
	public ResponseEntity<List<FuncionarioDTO>> listarFuncionarios() {
		return ResponseEntity.ok(funcionarioService.listar());
	}
	
	@DeleteMapping("/deletarFuncinario/{id}") //deu certo
	public ResponseEntity<Void> deletarfuncioanrio(@PathVariable Long id) {
		funcionarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/listarPedidos") //deu certo
	public ResponseEntity<List<PedidoDTO>> listar(){
		return ResponseEntity.ok(pedidoService.listaTodosPedidos());
	}
	
	@PutMapping("/editarStatusPedido/{id}")
	public ResponseEntity<PedidoDTO> editarStatus(@PathVariable Long id, @Valid @RequestBody PedidoAtualizarStatusDTO pedidoatt){
		PedidoDTO pedidoDTO = pedidoService.atualizarStatusPedido(id, pedidoatt);
		return ResponseEntity.ok(pedidoDTO);
	}
	
	@GetMapping("listarCliente/{id}")
	public  ResponseEntity<ClienteDTO> listarCliente(@PathVariable Long id){
		ClienteDTO clienteDTO = clienteService.listarId(id);
		return ResponseEntity.ok(clienteDTO);
	}
	
	@GetMapping(value = "/pedidos/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	public ResponseEntity<byte[]> gerarRelatorioExcel() throws IOException {
		
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Relatório de Pedidos");

	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("ID");
	    header.createCell(1).setCellValue("Cliente");
	    header.createCell(2).setCellValue("Data");
	    header.createCell(3).setCellValue("Valor Total");
	    header.createCell(4).setCellValue("Forma de Pagamento");
	    header.createCell(5).setCellValue("Status"); //adicionar mais colunas depois talvez?

	    List<PedidoDTO> pedidos = pedidoService.listaTodosPedidos(); 

	    int linha = 1;
	    for (PedidoDTO pedido : pedidos) {
	        Row row = sheet.createRow(linha++);
	        row.createCell(0).setCellValue(pedido.getId());
	        row.createCell(1).setCellValue(pedido.getEmailCliente());
	        row.createCell(2).setCellValue(pedido.getDataPedido().toString());
	        row.createCell(3).setCellValue(pedido.getValorFinal().doubleValue());
	        row.createCell(4).setCellValue(pedido.getFormaPgto().toString());
	        row.createCell(5).setCellValue(pedido.getStatus().toString());
	    }

	    workbook.write(out);
	    workbook.close();

	    HttpHeaders headers = new HttpHeaders();
	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-pedidos.xlsx");

	    return ResponseEntity.ok().headers(headers).body(out.toByteArray());
	}
	
}
