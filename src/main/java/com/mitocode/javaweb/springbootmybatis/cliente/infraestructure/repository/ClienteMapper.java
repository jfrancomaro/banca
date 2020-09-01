package com.mitocode.javaweb.springbootmybatis.cliente.infraestructure.repository;

import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mitocode.javaweb.springbootmybatis.cliente.domain.Cliente;

@Mapper
public interface ClienteMapper {

	@Select("SELECT * FROM cliente")
	public Collection<Cliente> findAll();

	@Insert("INSERT INTO public.cliente(nombres, documento, fecha_nacimiento, clave) VALUES( #{nombres}, #{documento}, #{fecha_nacimiento}, #{clave} ) ")
	public int insert(Cliente cliente);

	@Delete("DELETE FROM cliente WHERE id = #{idCliente}")
	public int delete(@Param("idCliente") Integer id);

	@Update("UPDATE cliente SET nombres=#{nombres}, documento=#{documento}, fecha_nacimiento=#{fecha_nacimiento}, clave=#{clave} WHERE id = #{id}")
	public int update(Cliente cliente);

	@Select("SELECT * FROM cliente WHERE documento= #{documento} AND clave = #{clave}")
	public Cliente findByDocumentoAndClave(String documento, String clave);
	
	@Select("SELECT * FROM cliente WHERE documento= #{documento}")
	public Cliente findByDocumento(String documento);
	
	@Select("SELECT * FROM cliente WHERE id = #{id}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "tarjetas", 
				javaType = Collection.class,
				column = "id",
				many = @Many(select = "com.mitocode.javaweb.springbootmybatis.tarjeta.infraestructure.repository.TarjetaMapper.findByIdCliente"))
	})
	public Cliente findById(Integer id);

}
