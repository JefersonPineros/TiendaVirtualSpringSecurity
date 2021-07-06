package co.com.tienda.virtual.back.api.entity.dto;
import java.util.List;

import co.com.tienda.virtual.back.api.entity.models.DatosEntrega;
import co.com.tienda.virtual.back.api.entity.models.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private Integer state;
	private String foto;
	
	private List<Role> roles;
	private DatosEntrega datosEntrega;

}
