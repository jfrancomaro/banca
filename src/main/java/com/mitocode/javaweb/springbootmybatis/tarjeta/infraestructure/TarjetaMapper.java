package com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.Tarjeta;
import com.mitocode.javaweb.springbootmybatis.tarjeta.domain.TarjetaEstado;

@Mapper
public interface TarjetaMapper {

	@Select("SELECT * FROM tarjeta WHERE id = #{#id}")
	@Results({
		@Result(property = "numeroTarjeta", column = "numero_tarjeta"),
		@Result(property = "fechaVencimiento", column = "fecha_vencimiento"),
		@Result(property = "estado",
				javaType = TarjetaEstado.class, 
				column = "estado"),
		@Result(property = "cliente", 
				javaType = Cliente.class,
				column = "id_cliente", 
				one = @One(select = "com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.ClienteMapper.findById"))
	})
	public Tarjeta findById(Integer id);

	@Select("SELECT * FROM tarjeta WHERE id_cliente = #{id_cliente}")	
	@Results({
		@Result(property = "numeroTarjeta", column = "numero_tarjeta"),
		@Result(property = "fechaVencimiento", column = "fecha_vencimiento"),
		@Result(property = "estado",
				javaType = TarjetaEstado.class, 
				column = "estado"),
		@Result(property = "cliente.id", 
				javaType = Integer.class,
				column = "id_cliente") 				
	})
	public Collection<Tarjeta> findByIdCliente(@Param("id_cliente") Integer idCliente);
	
	
	@Insert("INSERT INTO tarjeta(id_cliente, numero_tarjeta, fecha_vencimiento, estado) VALUES (#{cliente.id}, #{numeroTarjeta}, #{fechaVencimiento}, #{estado})")
	public Integer insert(Tarjeta tarjeta);
	
	@Select("SELECT * FROM tarjeta WHERE id_cliente = #{id_cliente} AND estado = #{estado}")
	@Results({@Result(property = "cliente.id", javaType = Integer.class, column = "id_cliente")})
	public Collection<Tarjeta> findByIdClienteAndEstado(@Param("id_cliente") Integer idCliente, String estado);
	
	
	@Update("UPDATE tarjeta SET estado=#{estado_nuevo} WHERE id = #{id}")
	public Integer updateByEstado(@Param("id") Integer idTarjeta, @Param("estado_nuevo") String estadNuevo);

	@Delete("DELETE FROM tarjeta WHERE id = #{id}")	
	public Integer delete(Integer id);
	
	@Update("UPDATE tarjeta SET fecha_vencimiento=#{fechaVencimiento}, estado=#{estado} WHERE id = #{id}")
	public Integer update(Tarjeta tarjeta);
	
}
