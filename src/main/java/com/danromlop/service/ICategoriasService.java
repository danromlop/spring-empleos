package com.danromlop.service;


import java.util.List;
import com.danromlop.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	
}

