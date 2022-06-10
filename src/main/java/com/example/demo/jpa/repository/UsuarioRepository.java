package com.example.demo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.jpa.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
