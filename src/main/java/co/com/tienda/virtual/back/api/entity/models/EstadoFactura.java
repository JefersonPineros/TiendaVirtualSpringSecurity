package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "ESTADO_FACTURA")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EstadoFactura implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ESTADO")
	private Integer id;
	
	@Column(name = "NOMBRE_ESTADO")
	private String nombreEstado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
