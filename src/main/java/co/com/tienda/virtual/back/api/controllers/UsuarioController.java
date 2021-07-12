package co.com.tienda.virtual.back.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.com.tienda.virtual.back.api.entity.dto.UsuarioDto;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;
import co.com.tienda.virtual.back.api.services.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping()
	public ResponseEntity<?> findAll() {
		List<UsuarioDto> listaUsuario = new ArrayList<UsuarioDto>();
		Map<String, Object> result = new HashMap<>();
		try {
			listaUsuario = usuarioService.findAll();
		} catch (Exception e) {
			result.put("message", "Se ha presentado un error al buscar el usuario");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listaUsuario.isEmpty()) {
			result.put("message", "No se han encontrado usuarios");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<UsuarioDto>>(listaUsuario, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		Map<String, Object> result = new HashMap<>();
		UsuarioDto user = null;

		try {
			user = usuarioService.findByEmail(email);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar el usuario");
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().toString());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			result.put("message", "El usuario: ".concat(email) + " no se encuentra registrado");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsuarioDto>(user, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<>();
		UsuarioDto user = null;

		try {
			user = usuarioService.findById(id);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar el usuario");
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().toString());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (user == null) {
			result.put("message", "El id: ".concat(String.valueOf(id)) + " no se encuentra registrado");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UsuarioDto>(user, HttpStatus.OK);
	}

	@PostMapping("/{roll}")
	public ResponseEntity<?> createUser(@RequestBody Usuarios usuario, @PathVariable Integer roll) {

		Map<String, Object> result = new HashMap<>();
		UsuarioDto user = null;
		
		try {
			user = usuarioService.createUser(usuario, roll);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al crear un usuario");
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (user == null) {
			result.put("message", "Se ha presentado un error al crear un usuario");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UsuarioDto>(user, HttpStatus.OK);
	}
}
