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
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "PRODUCTOS_FACTURA")
@Data
public class ProductosFactura implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productoFacturaId")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTO_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Productos producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FACTURA_ID")
	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Factura factura;
	
	@Column(name = "CANTIDAD_SOLICITADA")
	private Integer cantidad;
	
	@Column(name = "ESTADO_PRODUCTO_FACTURA")
	private Integer estado;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
