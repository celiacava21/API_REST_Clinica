package com.example.dao;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entities.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long>{
    
/**1. Método que devuelve lista de clientes, por página y siempre con ordenamiento */

@Query(value = "select c from Cliente c left join fetch c.hotel")
public List<Cliente> findAll(Sort sort);

/**Método que devuelve productos paginados */

@Query(value = "select c from Cliente c left join fetch c.hotel", 
countQuery = "select count(c) from Cliente c left join c.hotel")
public Page<Cliente> findAll(Pageable pageable);

/**Método que recupera producto por el ID */
@Query(value = "select c from Cliente c left join fetch c.hotel where c.id = :id")
public Cliente findById(long id);

}
