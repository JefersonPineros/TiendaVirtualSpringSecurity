package co.com.tienda.virtual.back.api.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.virtual.back.api.entity.models.Usuarios;

public interface IUsuarioDao extends JpaRepository<Usuarios, Integer>{
	public Usuarios findByEmail(String email);
}
