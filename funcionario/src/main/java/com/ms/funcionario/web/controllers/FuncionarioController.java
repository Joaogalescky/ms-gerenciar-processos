package com.ms.funcionario.web.controllers;

import com.ms.funcionario.entities.Funcionario;
import com.ms.funcionario.exceptions.FuncionarioNaoEncontradoException;
import com.ms.funcionario.services.FuncionarioService;
import com.ms.funcionario.web.dtos.FuncionarioCadastrarDto;
import com.ms.funcionario.web.dtos.FuncionarioListarDto;
import com.ms.funcionario.web.dtos.mappers.FuncionarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Funcionarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de funcionário.")
@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(
            summary = "Listar funcionários",
            description = "Recurso para listar todos os funcionários",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso listado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Funcionario.class)))
            }
    )
    @GetMapping
    public ResponseEntity<Iterable<Funcionario>> listarFuncionario() {
        Iterable<Funcionario> funcionarios = funcionarioService.listarFuncionario();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @Operation(
            summary = "Cadastrar funcionário",
            description = "Recurso para cadastrar funcionário",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<FuncionarioCadastrarDto> cadastrarFuncionario(@RequestBody FuncionarioCadastrarDto funcionarioCadastrarDto) {
        Funcionario funcionario = funcionarioService.cadastrarFuncionario(funcionarioCadastrarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapper.toDtoCadastrar(funcionario));
    }

    @Operation(
            summary = "Buscar funcionário",
            description = "Recurso para buscar funcionário por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso buscado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recurso não encontrado",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioListarDto> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(id);
        }
        return ResponseEntity.ok(FuncionarioMapper.toListDto(funcionario));
    }

    @Operation(
            summary = "Atualizar funcionário",
            description = "Recurso para atualizar funcionário",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso atualizado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Funcionário não encontrado no sistema",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioCadastrarDto> atualizarFun(@PathVariable Long id, @RequestBody FuncionarioListarDto funcionarioListarDto) throws Exception {
        Funcionario funcionario = funcionarioService.alterarFuncionario(id, funcionarioListarDto);
        return ResponseEntity.ok(FuncionarioMapper.toDtoCadastrar(funcionario));
    }

    @Operation(
            summary = "Deletar funcionário",
            description = "Recurso para deletar funcionário",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Recurso deletado com sucesso!",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Funcionário não encontrado no sistema",
                            content = @Content(
                                    mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}

