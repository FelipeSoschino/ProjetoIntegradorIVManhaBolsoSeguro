package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.dto.Request.GestaoDTORequest;
import com.senac.bolsoseguro.dto.Response.GestaoDTOResponse;
import com.senac.bolsoseguro.entity.Gestao;
import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.repository.GestaoRepository;
import com.senac.bolsoseguro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class GestaoService {

    @Autowired
    private final GestaoRepository gestaoRepository;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public GestaoService(GestaoRepository gestaoRepository,UsuarioRepository usuarioRepository,ModelMapper modelMapper) {
        this.gestaoRepository = gestaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }


    public List<Gestao> listarGestao(){ return gestaoRepository.findAll();}

    public GestaoDTOResponse criarGestao(int usuarioId,GestaoDTORequest dto) {
        // 1. Validar se o usuário existe (Gestao precisa de um dono)
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        // 2. Converter DTO para Entity
        Gestao gestao = modelMapper.map(dto, Gestao.class);
        System.out.println("ID da Gestão após ModelMapper: " + gestao.getId());
        System.out.println("Nome da Gestão: " + gestao.getNome());

        gestao.setUsuario(usuario);
        gestao.setDataCriacao(LocalDateTime.now());
        gestao.setStatus(1);

        // 4. Salvar no banco
        Gestao gestaoSalva = gestaoRepository.save(gestao);

        // 5. Retornar o DTO de Resposta
        return modelMapper.map(gestaoSalva, GestaoDTOResponse.class);
    }

    public GestaoDTOResponse atualizarGestao(int id, GestaoDTORequest dto) {
        // 1. Busca a gestão atual no banco ou lança um erro se não achar
        Gestao gestaoExistente = gestaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestão não encontrada com o ID: " + id));

        // 2. Atualiza os campos permitidos da entidade com os novos dados do DTO
        gestaoExistente.setNome(dto.getNome());
        gestaoExistente.setDataInicio(dto.getDataInicio());
        gestaoExistente.setDataFim(dto.getDataFim());
        gestaoExistente.setValorEstimado(dto.getValorEstimado());

        // Opcional: Se você tiver cálculo de período baseado nas datas, refaça-o aqui
        // gestaoExistente.setPeriodo(...);

        // 3. Salva as alterações no banco de dados (o save faz UPDATE se o ID já existir)
        Gestao gestaoAtualizada = gestaoRepository.save(gestaoExistente);

        // 4. Retorna mapeado para o DTO de resposta
        return modelMapper.map(gestaoAtualizada, GestaoDTOResponse.class);
    }
    public void apagarGestao(Integer gestaoId) {
        this.gestaoRepository.apagarGestao(gestaoId);
    }

}
