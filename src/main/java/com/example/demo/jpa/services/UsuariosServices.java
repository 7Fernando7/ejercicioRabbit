package com.example.demo.jpa.services;

import com.example.demo.jpa.modelo.Usuario;

public interface UsuariosServices {

  public Usuario findAll(Usuario user);

  public Usuario altaUsuario(Usuario user);

  public Usuario leerUsuario(int id);

  public Usuario editarUsuario(int id, Usuario usuario);

  public Usuario borrarUsuario(Usuario user);

  public Usuario findById(int id);

}
