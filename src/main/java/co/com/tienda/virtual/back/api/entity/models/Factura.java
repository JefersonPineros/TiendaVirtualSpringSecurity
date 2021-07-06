package co.com.tienda.virtual.back.api.entity.models;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "FACTURAS")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Factura implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FACTURA_ID")
	private Integer id;

	@Column(name = "TOTAL_FACTURA")
	private double totalFactura;

	@Column(name = "FECHA_COMPRA")
	@Temporal(TemporalType.DATE)
	private Date fechaCompra;

	@Column(name = "FECHA_DESPACHO")
	@Temporal(TemporalType.DATE)
	private Date fechaDespacho;

	@Column(name = "ESTADO_FACTURA")
	private int state;

	// Si se coloca el atributo CASACADE.ALL este intentara insertar si o si un
	// usuario nuevo y no asocia si el usuario ya existe
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuarios usuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factura", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<ProductosFactura> productos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO")
	//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EstadoFactura estadoFactura;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PAGO")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MetodosPago metodoPago;

	public Factura(Integer id, double totalFactura, Date fechaCompra, Date fechaDespacho, Usuarios usuario) {
		super();
		this.id = id;
		this.totalFactura = totalFactura;
		this.fechaCompra = fechaCompra;
		this.fechaDespacho = fechaDespacho;
		this.usuario = usuario;
	}

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
