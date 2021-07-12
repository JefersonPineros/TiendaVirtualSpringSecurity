package co.com.tienda.virtual.back.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.tienda.virtual.back.api.entity.dto.FacturaDto;
import co.com.tienda.virtual.back.api.entity.models.Factura;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;
import co.com.tienda.virtual.back.api.services.IFacturaService;
import co.com.tienda.virtual.back.api.services.IProductosService;
import co.com.tienda.virtual.back.api.services.IUsuarioService;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

	@Autowired
	private IFacturaService facturaService;
	
	@Autowired IUsuarioService usuarioService;
	
	@GetMapping()
	public ResponseEntity<?> findFacturas() {
		List<FacturaDto> listFacturas = null;
		Map<String, Object> result = new HashMap<>();
		try {
			listFacturas = facturaService.findAll();
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar las facturas");
			result.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listFacturas.isEmpty()) {
			result.put("message", "No se han encontrado facturas.");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<FacturaDto>>(listFacturas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findFacturaById(@PathVariable Integer id) {
		FacturaDto facturaBuscada = null;
		Map<String, Object> result = new HashMap<>();
		try {
			facturaBuscada = facturaService.findById(id);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar la factura");
			result.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (facturaBuscada == null) {
			result.put("message", "No se ha encontrado la factura: ".concat(String.valueOf(id)));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FacturaDto>(facturaBuscada, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createFactura(@RequestBody Factura factura) {
		FacturaDto newFactura = null;
		Map<String, Object> result = new HashMap<>();
		try {
			factura.setUsuario(usuarioService.findByIdCompleto(factura.getUsuario().getId()));
			newFactura = facturaService.createFactura(factura);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al generar la factura");
			result.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newFactura == null) {
			result.put("message", "Se ha presentado un error al generar la factura");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FacturaDto>(newFactura, HttpStatus.CREATED);
	}

	@PutMapping()
	public ResponseEntity<?> updateFactura(@RequestBody Factura factura) {
		Map<String, Object> result = new HashMap<>();
		FacturaDto facturaActualizada = null;
		try {
			Usuarios usuarioFactura = usuarioService.findByIdCompleto(factura.getUsuario().getId());
			factura.setUsuario(usuarioFactura);
			facturaActualizada = facturaService.updateFactura(factura);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al generar la factura");
			result.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (facturaActualizada == null) {
			result.put("message", "Se ha presentado un error al generar la factura");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FacturaDto>(facturaActualizada, HttpStatus.CREATED);
	}
}
