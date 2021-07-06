package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_roll")
	private Integer id;

	@Column(name = "nombre_roll",unique = true)
	private String nombre_role;
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
	//@JsonIgnore
	//private List<UsuariosRoles> usuarios;
	 	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
