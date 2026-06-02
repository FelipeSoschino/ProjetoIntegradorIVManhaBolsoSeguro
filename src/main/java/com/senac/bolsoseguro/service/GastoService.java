package com.senac.bolsoseguro.service;

import com.senac.bolsoseguro.dto.Request.GastoDTORequest;
import com.senac.bolsoseguro.dto.Request.GastoDTOUpdateRequest;
import com.senac.bolsoseguro.dto.Response.GastoDTOResponse;
import com.senac.bolsoseguro.entity.Categoria;
import com.senac.bolsoseguro.entity.Gasto;
import com.senac.bolsoseguro.entity.Gestao;
import com.senac.bolsoseguro.repository.CategoriaRepository;
import com.senac.bolsoseguro.repository.GastoRepository;
import com.senac.bolsoseguro.repository.GestaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GastoService {

    @Autowired
    private final GastoRepository gastoRepository;

    @Autowired
    private final CategoriaRepository categoriaRepository;

    @Autowired
    private final GestaoRepository gestaoRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public GastoService(GastoRepository gastoRepository,
                        CategoriaRepository categoriaRepository,
                        GestaoRepository gestaoRepository,
                        ModelMapper modelMapper) {
        this.gastoRepository = gastoRepository;
        this.categoriaRepository = categoriaRepository;
        this.gestaoRepository = gestaoRepository;
        this.modelMapper = modelMapper;
    }

    public GastoDTOResponse criarGasto(int gestaoId, int categoriaId, GastoDTORequest dto) {
        // 1. Validar se a Gestão existe (O gasto precisa pertencer a uma gestão)
        Gestao gestao = gestaoRepository.findById(gestaoId)
                .orElseThrow(() -> new RuntimeException("Gestão não encontrada"));

        // 2. Validar se a Categoria existe (O gasto precisa de uma categoria)
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // 3. Converter DTO para Entity
        Gasto gasto = modelMapper.map(dto, Gasto.class);

        // Logs opcionais para depuração, igual você fez no outro
        System.out.println("ID do Gasto após ModelMapper: " + gasto.getId());
        System.out.println("Descrição/Nome do Gasto: " + gasto.getDescricao());

        // 4. Vincular os relacionamentos e metadados
        gasto.setGestao(gestao);       // Vincula o gasto à gestão correspondente
        gasto.setCategoria(categoria); // Vincula o gasto à categoria correspondente
        gasto.setDataCriacao(LocalDateTime.now());
        gasto.setStatus(1); // Ative essa linha se o seu Gasto também tiver status

        // 5. Salvar no banco
        Gasto gastoSalvo = gastoRepository.save(gasto);

        // 6. Retornar o DTO de Resposta
        return modelMapper.map(gastoSalvo, GastoDTOResponse.class);
    }
    public List<GastoDTOResponse> listarGastosPorGestaoId(int gestaoId) {
        // 1. Opcional: Validar se a gestão existe antes de buscar os gastos
        if (!gestaoRepository.existsById(gestaoId)) {
            throw new RuntimeException("Gestão não encontrada");
        }

        // 2. Chama o método customizado com @Query que criamos no GastoRepository
        List<Gasto> gastos = gastoRepository.listarGastosPorGestaoId(gestaoId);

        // 3. Converte a lista de 'Gasto' para 'GastoDTOResponse'
        return gastos.stream()
                .map(gasto -> modelMapper.map(gasto, GastoDTOResponse.class))
                .collect(Collectors.toList());
    }
    public GastoDTOResponse atualizarGasto(int id, GastoDTOUpdateRequest dto) {
        // 1. Busca o Gasto existente (Mantém a gestão que já estava nele)
        Gasto gastoExistente = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado com o ID: " + id));

        // 2. Busca a Categoria escolhida para garantir que ela existe no banco
        Categoria novaCategoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + dto.getCategoriaId()));

        // 3. Atualiza apenas os campos permitidos
        gastoExistente.setNome(dto.getNome());
        gastoExistente.setDescricao(dto.getDescricao());
        gastoExistente.setValor(dto.getValor());
        gastoExistente.setCategoria(novaCategoria); // Altera a categoria para a nova escolhida

        // 4. Salva no banco de dados (A gestão original continua intacta no registro)
        Gasto gastoAtualizado = gastoRepository.save(gastoExistente);

        // 5. Retorna o DTO de resposta
        return modelMapper.map(gastoAtualizado, GastoDTOResponse.class);
    }

    public void apagarGasto(Integer gastoId){this.gastoRepository.apagarGasto(gastoId);}

}
