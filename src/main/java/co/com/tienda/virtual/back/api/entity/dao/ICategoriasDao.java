package co.com.tienda.virtual.back.api.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.virtual.back.api.entity.models.Categorias;

public interface ICategoriasDao extends JpaRepository<Categorias, Integer> {

}
