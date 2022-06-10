package com.example.demo.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.modelo.Usuario;
import com.example.demo.jpa.services.IntRabbitSender;
import com.example.demo.jpa.services.UsuariosServices;

@RestController
@RequestMapping("/usuariodb")
public class MicontrollerJpa {

  @Autowired
  private UsuariosServices userPrueba;
  @Autowired
  private IntRabbitSender rabbitSender;

  @GetMapping("/find-all")
  public ResponseEntity<Usuario> findeAll(final Usuario user) {
    return new ResponseEntity<Usuario>(userPrueba.findAll(user), HttpStatus.OK);

  }

  @PostMapping("/users")
  public ResponseEntity<Usuario> altaUsuario(@RequestBody final Usuario usuario) {
    return new ResponseEntity<Usuario>(userPrueba.altaUsuario(usuario), HttpStatus.OK);
  }

  @GetMapping(value = "/leer-usuario/{id}", produces = "application/json")
  public ResponseEntity<Usuario> getUsuario(@PathVariable final int id) {
    final Usuario userOptional = userPrueba.leerUsuario(id);
    rabbitSender.sendUsuario(userOptional);
    if (userOptional != null) {
      return new ResponseEntity<Usuario>(userOptional, HttpStatus.OK);
    }
    return new ResponseEntity<Usuario>(userOptional, HttpStatus.NOT_FOUND);

  }

  @PutMapping(value = "/editar-usuario/{id}")
  public ResponseEntity<Usuario> editar(@PathVariable final int id,
    @RequestBody final Usuario usuario) {
    final Usuario user = userPrueba.leerUsuario(id);
    if (user == null) {
      return new ResponseEntity<Usuario>(user, HttpStatus.NOT_FOUND);
    } else {
      final Usuario userActualizado = userPrueba.editarUsuario(id, usuario);
      return new ResponseEntity<Usuario>(userActualizado, HttpStatus.OK);
    }
  }

  @DeleteMapping(value = "/borrar-usuario/{id}")
  public ResponseEntity<Usuario> delete(@PathVariable final int id) {
    final Usuario user = userPrueba.leerUsuario(id);
    if (user == null) {
      return new ResponseEntity<Usuario>(user, HttpStatus.NOT_FOUND);
    } else {
      userPrueba.borrarUsuario(user);
      return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

  }

}
