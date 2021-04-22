package br.com.devinhouse.avaliacaomodulo2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devinhouse.avaliacaomodulo2.dto.ProcessoDTO;
import br.com.devinhouse.avaliacaomodulo2.repository.ProcessoRepository;

@Service
public class ProcessoService {
	@Autowired
	private ProcessoRepository repos;

	private List<ProcessoDTO> recuperaProcessos() {
		return repos.findAllProcessos();
	}

	public List<ProcessoDTO> getProcessos() {
		return recuperaProcessos();
	}

	public ProcessoDTO findByIdProcesso(Integer id) {

		List<ProcessoDTO> processos = recuperaProcessos();

		ProcessoDTO processosPorId = new ProcessoDTO();

		for (ProcessoDTO pDTO : processos) {
			if (id.equals(pDTO.getId())) {
				processosPorId=pDTO;
			}
		}
		return processosPorId;

	}

	public ProcessoDTO findByChaveProcesso(String chave) {

		List<ProcessoDTO> processos = recuperaProcessos();

		ProcessoDTO processosPorChave = new ProcessoDTO();

		for (ProcessoDTO pDTO : processos) {
			if (chave.equals(pDTO.getChaveProcesso())) {
				processosPorChave  = pDTO;
			}
		}

		return processosPorChave;
	}

	public List<ProcessoDTO> createProcesso(ProcessoDTO processo) {
		List<ProcessoDTO> processos = getProcessos();

		for (ProcessoDTO pDTO : processos) {
			if (processo.getId().equals(pDTO.getId())) {
				processo.setId(processo.getId() + 1);
				processo.setNuProcesso(processo.getNuProcesso() + 1);
				processo.setCdAssunto(processo.getCdAssunto() + 1);
				processo.setCdInteressado(processo.getCdInteressado() + 1);
			}
		}
		processos.add(processo);

		return processos;
	}
	
	
	
	public List<ProcessoDTO> updateProcesso(Integer id, ProcessoDTO processo) {

		List<ProcessoDTO> processos = recuperaProcessos();

		for (ProcessoDTO pDTO : processos) {

			
			if (id.equals(pDTO.getId())) {
				if(processo.getChaveProcesso() != null) {
					pDTO.setChaveProcesso(processo.getChaveProcesso());					
				}
				if(processo.getSgOrgaoProcesso() != null) {
					pDTO.setSgOrgaoProcesso(processo.getSgOrgaoProcesso());					
				}
				if(processo.getNuAnoProcesso() != null) {
					pDTO.setNuAnoProcesso(processo.getNuAnoProcesso());					
				}
				if(processo.getDescricao() != null) {
					pDTO.setDescricao(processo.getDescricao());					
				}
				if(processo.getDescricaoAssunto() != null) {
					pDTO.setDescricaoAssunto(processo.getDescricaoAssunto());					
				}
				if(processo.getNmInteressado() != null) {
					pDTO.setNmInteressado(processo.getNmInteressado());					
				}
				
			}

		}
		return processos;
	}

	public void deleteProcesso(Integer id) {

		List<ProcessoDTO> processos = getProcessos();

		List<ProcessoDTO> processosPorId = new ArrayList<ProcessoDTO>();

		for (ProcessoDTO pDTO : processos) {
			if (id.equals(pDTO.getId())) {
				processosPorId.remove(pDTO);
			}
		}

	}

}
