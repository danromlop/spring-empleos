package com.danromlop.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.danromlop.model.Categoria;
import com.danromlop.repository.CategoriasRepository;
import com.danromlop.service.ICategoriasService;

@Service
//@Primary

public class CategoriasServiceJpa implements ICategoriasService {
	
	@Autowired
	private CategoriasRepository categoriasRepo;

	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {

		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
