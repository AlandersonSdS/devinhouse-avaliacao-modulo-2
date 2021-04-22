package br.com.devinhouse.avaliacaomodulo2.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.devinhouse.avaliacaomodulo2.dto.ProcessoDTO;
import br.com.devinhouse.avaliacaomodulo2.exception.ResourceNotFoundException;
import br.com.devinhouse.avaliacaomodulo2.service.ProcessoService;

@RestController
@RequestMapping("/v1/processos")
public class ProcessoController {
	
	
	@Autowired
	private ProcessoService service;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<ProcessoDTO> geraListaProcessos() {
		return service.getProcessos();
	}
	
	@GetMapping(path = "/processo/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProcessoDTO> getById(@PathVariable Integer id) {
		ProcessoDTO processo = service.findByIdProcesso(id);
		if(processo.getId() == null) {
			throw new ResourceNotFoundException("Processo não encontrado pelo ID: " + id);
		}
		return new ResponseEntity<>(processo, HttpStatus.OK);
	}
	
	@GetMapping(path = "/processo", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ProcessoDTO> getByChaveProcesso(@RequestParam String chave) {
		ProcessoDTO processo = service.findByChaveProcesso(chave);
		if(processo.getId() == null) {
			throw new ResourceNotFoundException("Processo não encontrado pelo Chave: " + chave);
		}
		return new ResponseEntity<>(processo, HttpStatus.OK);
	}
	
	@PostMapping(path = "/cadastro/processo", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public List<ProcessoDTO> createProcesso(@RequestBody ProcessoDTO create) {
		return service.createProcesso(create);
	}
	
	
	
	@PutMapping(path = "/atualizar/processo/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProcessoDTO> atualizarProcesso(@PathVariable Integer id, @RequestBody ProcessoDTO processo) {
		return service.updateProcesso(id, processo);
	}
	
	

	@DeleteMapping(path = "/deletar/processo/{id}", consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteProcesso(@PathVariable Integer id) {
		
		service.deleteProcesso(id);
	}


}
