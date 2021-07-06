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
@Table(name = "METODOS_PAGO")
@Data
public class MetodosPago implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGO")
	private Integer id;
	
	@Column(name = "TIPO_PAGO")
	private String tipoPago;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
