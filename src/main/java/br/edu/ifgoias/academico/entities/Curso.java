package br.edu.ifgoias.academico.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Curso implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcurso;
	
	@Column (name = "nomecurso", nullable= false )
	private String nomecurso;
	
	@OneToMany (mappedBy = "curso")
	@JsonIgnore
	private List<Aluno> alunos = new ArrayList<>();

	public Curso() {

	}
	
	public Curso(Integer idcurso, String nome) {
		this.idcurso = idcurso;
		this.nomecurso = nome;
	}

	public Integer getIdcurso() {
		return idcurso;
	}

	public void setIdcurso(Integer idcurso) {
		this.idcurso = idcurso;
	}

	public String getNomecurso() {
		return nomecurso;
	}

	public void setNomecurso(String nome) {
		this.nomecurso = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void addAluno (Aluno a) {
			this.alunos.add(a);
			a.setCurso(this);
	}
	
	public void removeAluno (Aluno a) {
		
		if (this.alunos.contains(a)) {
			this.removeAluno(a);
			a.setCurso(null);
		}
	}

	@Override
	public String toString() {
		return "Curso [idcurso=" + idcurso + ", nomecurso=" + nomecurso + "]";
	}
		
}
