package com.danromlop.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.danromlop.model.Vacante;
import com.danromlop.repository.VacantesRepository;
import com.danromlop.service.IVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IVacantesService {
	
	@Autowired
	private VacantesRepository vacantesRepo;
	

	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		// TODO Auto-generated method stub
		Optional<Vacante> optional = vacantesRepo.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		vacantesRepo.save(vacante);

	}

	@Override
	public List<Vacante> buscarDestacadas() {
		
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

}
