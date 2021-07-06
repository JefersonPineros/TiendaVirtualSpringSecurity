package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "USUARIOS")
@Data
public class Usuarios implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Integer id;

	@Column(name = "NOMBRE_USUARIO")
	private String nombre;

	@Column(name = "APELLIDO_USUARIO")
	private String apellido;

	@Column(name = "PASSWORD_USUARIO")
	private String password;

	@Column(name = "EMAIL_USUARIO", unique = true)
	private String email;

	@Column(name = "FECHA_CREACION")
	@Temporal(TemporalType.DATE)
	private Date fechaCreacion;

	@Column(name = "FECHA_LOGUEO")
	@Temporal(TemporalType.DATE)
	private Date fechaLogueo;
	private Integer state;

	@Column(name = "IMAGEN_USUARIO")
	private String foto;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
	//@Column(name = "role_id")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) })
	private List<Role> roles;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ENTREGA")
	@JsonManagedReference
	private DatosEntrega datosEntrega;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
