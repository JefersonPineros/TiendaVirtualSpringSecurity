package co.com.tienda.virtual.back.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.tienda.virtual.back.api.entity.models.Categorias;
import co.com.tienda.virtual.back.api.entity.models.Productos;
import co.com.tienda.virtual.back.api.services.IProductosService;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

	private Logger log = LoggerFactory.getLogger(ProductosController.class);
	
	@Autowired
	private IProductosService  productosService;
	
	@GetMapping
	public ResponseEntity<?> findAllProducts() {
		List<Productos> listadoPro = null;
		Map<String, Object> result = new HashMap<>();
		try {
			listadoPro = productosService.findAll();
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error");
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(listadoPro.isEmpty()) {
			result.put("message", "No se han encontrado resultados");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Productos>>(listadoPro, HttpStatus.OK);
	}
	
	@GetMapping("/codigo/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) {
		Productos producto = null;
		Map<String, Object> result = new HashMap<>();
		try {
			producto = productosService.findByCode(code);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar el producto: ".concat(code));
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			result.put("message", "No se ha encontrado el producto: ".concat(code));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Productos>(producto, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Productos producto = null;
		Map<String, Object> result = new HashMap<>();
		try {
			producto = productosService.findById(id);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar el producto: ".concat(String.valueOf(id)));
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			result.put("message", "No se ha encontrado el producto: ".concat(String.valueOf(id)));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Productos>(producto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody Productos producto) {
		Map<String, Object> result = new HashMap<>();
		Productos product = null;
		try {
			product = productosService.createProduct(producto);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al crear el producto: ".concat(product.getNombre()));
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(producto == null) {
			result.put("error", "No se pudo crear el producto: ".concat(product.getNombre()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Productos>(productosService.createProduct(producto), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody Productos producto) {
		Map<String, Object> result = new HashMap<>();
		Productos proActual = null;
		Productos proActualizado = null;
		try {
			proActual = productosService.findById(producto.getId());
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al buscar el producto: ".concat(producto.getNombre()));
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (proActual == null) {
			result.put("error", "No se pudo encontrar el producto: ".concat(producto.getNombre()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		proActual.setNombre(producto.getNombre());
		proActual.setDescripcion(producto.getDescripcion());
		proActual.setStock(producto.getStock());
		proActual.setValor(producto.getValor());
		
		try {
			proActualizado = productosService.createProduct(proActual);
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error al actualizar el producto: ".concat(producto.getNombre()));
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (proActualizado == null) {
			result.put("error", "No se pudo actualizar el producto: ".concat(producto.getNombre()));
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Productos>(proActualizado, HttpStatus.OK);
		
	}
	
	@GetMapping("/categorias")
	public ResponseEntity<?> getAllCategorias(){
		List<Categorias> listadoCategorias = null;
		Map<String, Object> result = new HashMap<>();
		try {
			listadoCategorias = productosService.findaAllCategorias();
		} catch (DataAccessException e) {
			result.put("message", "Se ha presentado un error");
			result.put("error", e.getMessage().concat(": ") + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(listadoCategorias.isEmpty()) {
			result.put("message", "No se han encontrado resultados");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Categorias>>(listadoCategorias, HttpStatus.OK);
	}
	
	/*
	 * 
	 * @GetMapping("/categorias/{id}")
	public ResponseEntity<?> getCategoriaById(@PathVariable Integer id) {
		Categorias categoria = null;
		Map<String, Object> result = new HashMap<>();
		try {
			categoria = productosService.findByIdCategorias(id);
		} catch (DataAccessException e) {
			result.put("message", "Se presento un error al buscar la categoria");
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().getMessage());
		0				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(categoria == null) {
			result.put("message", "No se pudo encontrar la categoria");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Categorias>(categoria, HttpStatus.OK);
	}
	 */
	
	@PostMapping("/categorias")
	public ResponseEntity<?> createCategorias(@RequestBody Categorias categoria) {
		
		Categorias newCategoria = null;
		Map<String, Object> result = new HashMap<>();
		
		try {
			newCategoria = productosService.createCategorias(newCategoria);
		} catch (DataAccessException e) {
			result.put("message", "Se presento un error al crear la categoria: ".concat(categoria.getNombre()));
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().getMessage());
			
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (newCategoria == null) {
			result.put("message", "No se pudo crear la categoria");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Categorias>(newCategoria, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<?> updateCategorias(@PathVariable Integer id) {
		Map<String, Object> result = new HashMap<>();
		try {
			productosService.deleteCategorias(id);
		} catch (DataAccessException e) {
			result.put("message", "Se presento un error al eliminar la categoria");
			result.put("error", e.getMessage().concat(":") + e.getMostSpecificCause().getMessage());
			
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		result.put("message", "Se elimino exitosamente la categoria.");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
}
