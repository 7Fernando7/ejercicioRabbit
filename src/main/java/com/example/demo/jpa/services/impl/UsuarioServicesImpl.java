package com.example.demo.jpa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jpa.modelo.Usuario;
import com.example.demo.jpa.repository.UsuarioRepository;
import com.example.demo.jpa.services.UsuariosServices;

@Service
public class UsuarioServicesImpl implements UsuariosServices {

  @Autowired
  private UsuarioRepository userPrueba;

  @Override
  public Usuario findAll(final Usuario user) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Usuario altaUsuario(final Usuario user) {
    return userPrueba.save(user);
  }

  @Override
  public Usuario leerUsuario(final int id) {
    final Usuario user = userPrueba.findById(id).orElse(null);
    return user;
  }

  @Override
  public Usuario editarUsuario(final int id, final Usuario usuario) {

    final Usuario user = leerUsuario(id);

    user.setNombre(usuario.getNombre());
    user.setApellido(usuario.getApellido());
    user.setEmail(usuario.getEmail());
    user.setDni(usuario.getDni());
    user.setObservaciones(usuario.getObservaciones());
    final Usuario usuarioActualizado = userPrueba.save(user);
    return usuarioActualizado;
  }

  @Override
  public Usuario borrarUsuario(final Usuario user) {
    userPrueba.delete(user);
    return user;
  }

@Override
public Usuario findById(int id) {
	return userPrueba.findById(id).orElse(null);
}

}
