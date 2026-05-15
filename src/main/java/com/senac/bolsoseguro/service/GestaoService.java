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


}
