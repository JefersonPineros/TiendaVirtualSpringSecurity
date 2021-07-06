package co.com.tienda.virtual.back.api.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.tienda.virtual.back.api.entity.models.Role;

public interface IRoleDao extends JpaRepository<Role, Integer>{

}
