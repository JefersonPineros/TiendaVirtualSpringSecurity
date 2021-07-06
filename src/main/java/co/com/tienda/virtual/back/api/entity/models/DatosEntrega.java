package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "DATOS_ENTREGA")
@Data
public class DatosEntrega implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ENTREGA")
	private Integer id;
	
	@Column(name = "DIRECCION_CONTACTO", length = 100)
	private String direccion;
	
	@Column(name = "NUMERO_CONTACTO", length = 15)
	private String numeroContacto;
	
	@OneToOne(mappedBy = "datosEntrega", cascade = CascadeType.ALL)
	@JsonBackReference
	private Usuarios usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIUDAD")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Ciudades ciudad;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
