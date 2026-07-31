package com.dom.bean_lifecycle_demo.repository;

import com.dom.bean_lifecycle_demo.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
