package co.com.tienda.virtual.back.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.tienda.virtual.back.api.entity.dao.IUsuarioDao;
import co.com.tienda.virtual.back.api.entity.dto.UsuarioDto;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;
import co.com.tienda.virtual.back.api.utils.ConstruirDto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private ConstruirDto constFact;

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
		UsuarioDto user = null;
		Usuarios usuario = usuarioDao.findById(id).orElse(null);
		if (usuario != null) {
			user = constFact.buildUsuario(usuario);
		}
		
		return user;
	}

	@Override
	public UsuarioDto findByEmail(String email) {
		UsuarioDto user = null;
		Usuarios usuario = usuarioDao.findByEmail(email);
		if (usuario != null) {
			user = constFact.buildUsuario(usuario);
		}
		return user;
	}

	@Override
	public Usuarios findByEmailCompleto(String email) {
		return usuarioDao.findByEmail(email);
	}

	@Override
	public UsuarioDto createUser(Usuarios usuario, Integer roll) throws DataAccessException {
		UsuarioDto user = null;
		Usuarios usuarioCreado = usuarioDao.save(usuario);
		if (usuarioCreado != null) {			
			user = constFact.buildUsuario(usuarioCreado);
		}
		return user;
	}

	@Override
	public UsuarioDto updateUser(Usuarios usuario) {
		
		return null;
	}

	@Override
	public void deleteUsers(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuarios findByIdCompleto(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios user = usuarioDao.findByEmail(username);
		if (user == null) {
			log.error("Error en el loggin:".concat(username).concat(" No existe en el sistema"));
		}
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map( role -> new SimpleGrantedAuthority(role.getNombre_role()))
				.peek(authority -> log.info("roll: ".concat(authority.getAuthority())))
				.collect(Collectors.toList());
		User usuarioLogin  = new User(user.getEmail(), user.getPassword(), user.isState(), true, true, true, authorities);

		return usuarioLogin;
	}
}
