package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.dto.Request.UsuarioDTORequest;
import com.senac.bolsoseguro.dto.Response.UsuarioDTOResponse;
import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<Usuario> listarUsuarios(){return usuarioRepository.findAll();}

//    public Usuario criarUsuario(UsuarioDTORequest dto) {
//        // 1. Converte o DTO recebido para a Entidade Usuario
//        Usuario usuario = modelMapper.map(dto, Usuario.class);
//
//        // 2. Define os campos automáticos que não vêm do Request
//        usuario.setData(LocalDateTime.now()); //
//        usuario.setStatus(1); // 1 para Ativo por padrão
//
//        // 3. Salva no banco usando o Repository
//        return usuarioRepository.save(usuario);  //está retornando um usuário e nao um Response
//    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest dto) {
        // 1. Converte o DTO recebido para a Entidade Usuario
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        // 2. Define os campos automáticos que não vêm do Request
        usuario.setData(LocalDateTime.now()); //
        usuario.setStatus(1); // 1 para Ativo por padrão
        Usuario usuarioSave = usuarioRepository.save(usuario);
        // 3. Salva no banco usando o Repository
        return modelMapper.map(usuarioSave, UsuarioDTOResponse.class);
    }

    public UsuarioDTOResponse atualizarUsuario(Integer usuarioId, UsuarioDTORequest usuarioDTORequest) {
        Usuario usuario = this.usuarioRepository.listarUsuarioPorId(usuarioId);
        if (usuario != null) {
            modelMapper.map(usuarioDTORequest, usuario);
            Usuario usuarioSave = this.usuarioRepository.save(usuario);
            UsuarioDTOResponse usuarioDTOResponse = modelMapper.map(usuarioSave, UsuarioDTOResponse.class);
            return usuarioDTOResponse;
        } else {
            return null;
        }
    }


    public void apagarUsuario(Integer usuarioId) {
        this.usuarioRepository.apagarUsuario(usuarioId);
    }
}
