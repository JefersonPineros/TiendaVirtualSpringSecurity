package co.com.tienda.virtual.back.api.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import co.com.tienda.virtual.back.api.entity.dao.IUsuarioDao;
import co.com.tienda.virtual.back.api.entity.dto.UsuarioDto;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;
import co.com.tienda.virtual.back.api.utils.ConstruirFactura;

@Service
public class UsuarioService implements IUsuarioService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	// @Autowired
	// private IUsuarioRoleDao usuarioRoleDao;

	@Autowired
	private ConstruirFactura constFact;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public List<UsuarioDto> findAll() {
		List<Usuarios> listadoUsuarios = usuarioDao.findAll();
		List<UsuarioDto> listadoFinal = new ArrayList<UsuarioDto>();

		if (!listadoUsuarios.isEmpty()) {
			for (Usuarios usuario : listadoUsuarios) {
				UsuarioDto usuarioFinal = constFact.buildUsuario(usuario);
				listadoFinal.add(usuarioFinal);
			}
		}

		return listadoFinal;
	}

	@Override
	public UsuarioDto findById(Integer id) {
		Usuarios usuario = usuarioDao.findById(id).orElse(null);
		UsuarioDto user = constFact.buildUsuario(usuario);
		return user;
	}

	@Override
	public UsuarioDto findByEmail(String email) {
		Usuarios usuario = usuarioDao.findByEmail(email);
		UsuarioDto user = constFact.buildUsuario(usuario);
		return user;
	}

	@Override
	public UsuarioDto createUser(Usuarios usuario, Integer roll) throws DataAccessException {
		Usuarios usuarioCreado = usuarioDao.save(usuario);
		UsuarioDto user = constFact.buildUsuario(usuarioCreado);
		return user;
	}

	@Override
	public Usuarios updateUser(Usuarios usuario) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUsers(Integer id) {
		// TODO Auto-generated method stub

	}

}
