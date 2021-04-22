package br.com.devinhouse.avaliacaomodulo2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.devinhouse.avaliacaomodulo2.dto.ProcessoDTO;
import br.com.devinhouse.avaliacaomodulo2.mock.ProcessoMock;

@Repository
public class ProcessoRepository{
	
	@Autowired
	ProcessoMock processos;
	
	public List<ProcessoDTO> findAllProcessos() {
		return processos.getAllProcessos();
	}
}
