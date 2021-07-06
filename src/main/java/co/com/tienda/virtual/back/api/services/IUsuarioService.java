package co.com.tienda.virtual.back.api.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import co.com.tienda.virtual.back.api.entity.dto.UsuarioDto;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;

public interface IUsuarioService {

	public List<UsuarioDto> findAll();
	
	public UsuarioDto findById(Integer id);
	
	public UsuarioDto findByEmail(String email);
	
	public UsuarioDto createUser(Usuarios usuario, Integer roll) throws DataAccessException;
	
	public Usuarios updateUser(Usuarios usuario);
	
	public void deleteUsers(Integer id);
}
