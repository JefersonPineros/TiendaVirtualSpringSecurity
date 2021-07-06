package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "PRODUCTOS")
@Data
public class Productos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCTO_ID")
	private Integer id;
	@Column(length = 30, nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String codigo;
	@Column(nullable = false)
	private double valor;
	private Integer stock;
	private Integer state;
	@Column(length = 300, nullable = false)
	private String descripcion;
	@Column(nullable = false)
	private String nombreImagen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	@JsonIgnore
	private List<ProductosFactura> facturas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATEGORIA")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Categorias categorias;

	public Productos(Integer id, String nombre, String codigo, double valor, Integer stock, Integer state,
			String descripcion, String nombreImagen, List<ProductosFactura> facturas, Categorias categorias) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.valor = valor;
		this.stock = stock;
		this.state = state;
		this.descripcion = descripcion;
		this.nombreImagen = nombreImagen;
		this.facturas = facturas;
		this.categorias = categorias;
	}

	public Productos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
