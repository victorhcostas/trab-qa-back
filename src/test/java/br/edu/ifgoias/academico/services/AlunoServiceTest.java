package br.edu.ifgoias.academico.services;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifgoias.academico.entities.Aluno;
import br.edu.ifgoias.academico.repositories.AlunoRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {
	
	@Mock
	AlunoRepository repository;
	
	@InjectMocks
	AlunoService service;
	
    @Test
    @DisplayName("Testando Update")
    public void testUpdate() {
        // Mocking the dependencies
        //AlunoRepository repository = mock(AlunoRepository.class);

        // Creating an instance of service with the mocked repository
        // service.setAlunoRepository(repository);

        // Creating a sample Aluno object for testing
        Aluno alunoToUpdate = new Aluno();
        alunoToUpdate.setIdaluno(1); // Set a valid ID for testing

        // Creating a sample Aluno object with updated values
        Aluno updatedAluno = new Aluno();
        updatedAluno.setNome("Updated Name");
        updatedAluno.setSexo("F");
        updatedAluno.setDt_nasc(Date.valueOf("2010-05-05"));

        // Mocking the behavior of the repository findById method
        when(repository.findById(1)).thenReturn(Optional.of(alunoToUpdate));

        // Calling the update method
        Aluno result = service.update(1, updatedAluno);

        // Verifying that save method was called on the repository
        verify(repository, times(1)).save(any());

        // Verifying that the save method was called with the expected Aluno object
        ArgumentCaptor<Aluno> alunoCaptor = ArgumentCaptor.forClass(Aluno.class);
        verify(repository).save(alunoCaptor.capture());
        Aluno savedAluno = alunoCaptor.getValue();

        // Asserting that the saved Aluno has the expected updated values
        assertEquals(updatedAluno.getNome(), savedAluno.getNome());
        assertEquals(updatedAluno.getSexo(), savedAluno.getSexo());
        assertEquals(updatedAluno.getDt_nasc(), savedAluno.getDt_nasc());

        // Asserting that the result returned by the update method is the same as the saved Aluno
        assertEquals(savedAluno, result);
    }
}
