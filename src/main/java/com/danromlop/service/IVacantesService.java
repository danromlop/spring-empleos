package com.danromlop.service;

import java.util.List;

import com.danromlop.model.Vacante;

public interface IVacantesService {
	
	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(Integer idVacante);
	
	void guardar(Vacante vacante);
	
	List<Vacante> buscarDestacadas();
}
