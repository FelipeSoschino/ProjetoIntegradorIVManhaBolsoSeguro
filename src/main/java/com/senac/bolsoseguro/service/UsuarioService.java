package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    public List<Usuario> listarUsuarios(){return usuarioRepository.findAll();}
}
