package com.danromlop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danromlop.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
