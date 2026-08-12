package com.dom.bean_lifecycle_demo.repository;

import com.dom.bean_lifecycle_demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
