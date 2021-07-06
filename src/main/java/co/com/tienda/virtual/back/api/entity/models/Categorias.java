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
@Table(name = "CATEGORIAS_PRODUCTOS")
@Data
public class Categorias implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CATEGORIA")
	private Integer id;
	
	@Column(name = "NOMBRE_CATEGORIA", unique = true)
	private String nombre;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
