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
@Table(name = "CIUDADES")
@Data
public class Ciudades implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CIUDAD")
	private Integer idCiudad;
	@Column(name = "NOMBRE_CIUDAD")
	private String nombreCiudad;
	@Column(name = "CODIGO_POSTAL")
	private String codigoPostal;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
