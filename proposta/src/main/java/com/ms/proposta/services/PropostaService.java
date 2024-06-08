package com.ms.proposta.services;

import com.ms.proposta.Enum.Status;
import com.ms.proposta.entities.Proposta;
import com.ms.proposta.exceptions.IdPropostaNaoEncontradoException;
import com.ms.proposta.repositories.FuncionarioClient;
import com.ms.proposta.repositories.PropostaRepository;
import com.ms.proposta.web.dtos.FuncionarioDto;
import com.ms.proposta.web.dtos.PropostaAtualizacaoDto;
import com.ms.proposta.web.dtos.PropostaCadastroDto;
import com.ms.proposta.web.exceptions.FuncionarioNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropostaService {

    @Autowired
    private final PropostaRepository propostaRepository;

    @Autowired
    private final FuncionarioClient funcionarioClient;

    public Proposta cadastrarProposta(PropostaCadastroDto propostaDto) {

        FuncionarioDto funcionario = funcionarioClient.buscarPorId(propostaDto.getIdFuncionario());
        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(propostaDto.getIdFuncionario());
        }

        Proposta proposta = new Proposta();
        proposta.setIdFuncionario(propostaDto.getIdFuncionario());
        proposta.setNome(propostaDto.getNome());
        proposta.setDescricao(propostaDto.getDescricao());
        proposta.setDataProposta(new Date());
        Proposta savedProposta = propostaRepository.save(proposta);


        return savedProposta;
    }

    public List<Proposta> listarPropostas() {
        return propostaRepository.findAll();
    }

    public Proposta buscarPorId(Long id) {
        return propostaRepository.findById(id).orElseThrow(
                () -> new IdPropostaNaoEncontradoException(String.format("Proposta id=%s não encontrada", id))
        );
    }

    public Proposta alterarProposta(Long idProposta, PropostaAtualizacaoDto propostaDto) {
        Proposta propostaExistente = buscarPorId(idProposta);

        propostaExistente.setNome(propostaDto.getNome());
        propostaExistente.setDescricao(propostaDto.getDescricao());

        return propostaRepository.save(propostaExistente);
    }

    public void deletarProposta(Long id) {
        Proposta proposta = buscarPorId(id);
        propostaRepository.delete(proposta);
    }
}
