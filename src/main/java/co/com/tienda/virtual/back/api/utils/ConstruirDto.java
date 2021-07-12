package co.com.tienda.virtual.back.api.utils;

import org.springframework.stereotype.Component;

import co.com.tienda.virtual.back.api.entity.dto.FacturaDto;
import co.com.tienda.virtual.back.api.entity.dto.UsuarioDto;
import co.com.tienda.virtual.back.api.entity.models.Factura;
import co.com.tienda.virtual.back.api.entity.models.Usuarios;

@Component
public class ConstruirDto {

	public FacturaDto buildFactura(Factura factura) {
		UsuarioDto usuarioFinal = null;
		
		if (factura != null) {
			
			usuarioFinal = buildUsuario(factura.getUsuario());
			
			return FacturaDto.builder()
					.Id(factura.getId())
					.totalFactura(factura.getTotalFactura())
					.fechaCompra(factura.getFechaCompra())
					.fechaDespacho(factura.getFechaDespacho())
					.state(factura.getState())
					.usuario(usuarioFinal)
					.productos(factura.getProductos())
					.estadoFactura(factura.getEstadoFactura())
					.metodoPago(factura.getMetodoPago())
					.build();
		}
		return null;
	}
	
	public UsuarioDto buildUsuario(Usuarios usuario) {
		 return UsuarioDto.builder()
				.id(usuario.getId())
				.nombre(usuario.getNombre())
				.apellido(usuario.getApellido())
				.email(usuario.getEmail())
				.state(usuario.isState())
				.foto(usuario.getFoto())
				.roles(usuario.getRoles())
				.datosEntrega(usuario.getDatosEntrega())
				.build();
	}
}
