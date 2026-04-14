package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.entity.Usuario;
import com.senac.bolsoseguro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    public List<Usuario> listarUsuarios(){return usuarioRepository.findAll();}
}
