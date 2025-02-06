package com.danromlop.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.danromlop.model.Vacante;

@Service
public class VacantesServiceImpl implements IVacantesService {
	
	private List<Vacante> lista;
	
	public VacantesServiceImpl() {
		
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			lista = new LinkedList<Vacante>();
			try {
				// Creamos la oferta de Trabajo 1
				Vacante vacante1 = new Vacante();
				vacante1.setId(1);
				vacante1.setNombre("Ingeniero Civil");
				vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
				vacante1.setFecha(sdf.parse("09-02-2019"));
				vacante1.setSalario(8500.0);
				vacante1.setDestacado(1);
				vacante1.setImagen("empresa1.png");
				vacante1.setEstatus("Creada");
				
				// Creamos la oferta de Trabajo 2
				Vacante vacante2 = new Vacante();
				vacante2.setId(2);
				vacante2.setNombre("Desarrollador Java");
				vacante2.setDescripcion("Buscamos Desarrollador Java con experiencia en Spring Framework.");
				vacante2.setFecha(sdf.parse("15-03-2019"));
				vacante2.setSalario(12000.0);
				vacante2.setDestacado(0);
				vacante2.setImagen("empresa2.png");
				vacante2.setEstatus("Creada");

				// Creamos la oferta de Trabajo 3
				Vacante vacante3 = new Vacante();
				vacante3.setId(3);
				vacante3.setNombre("Diseñador Gráfico");
				vacante3.setDescripcion("Se necesita Diseñador Gráfico para crear materiales publicitarios.");
				vacante3.setFecha(sdf.parse("20-04-2019"));
				vacante3.setSalario(9500.0);
				vacante3.setDestacado(0);
				vacante3.setEstatus("Creada");
				
				

				// Creamos la oferta de Trabajo 4
				Vacante vacante4 = new Vacante();
				vacante4.setId(4);
				vacante4.setNombre("Analista de Datos");
				vacante4.setDescripcion("Se requiere Analista de Datos para interpretar grandes volúmenes de información.");
				vacante4.setFecha(sdf.parse("12-05-2019"));
				vacante4.setSalario(10500.0);
				vacante4.setDestacado(1);
				vacante4.setImagen("empresa3.png");
				vacante4.setEstatus("Creada");
				
				/** 
				 * Agregamos los 4 objetos de tipo Vacante a la lista*/
				lista.add(vacante1);
				lista.add(vacante2);
				lista.add(vacante3);
				lista.add(vacante4);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	
			
		}
		
	

	@Override
	public List<Vacante> buscarTodas() {
		// TODO Auto-generated method stub
		return lista;
	}



	@Override
	public Vacante buscarPorId(Integer idVacante) {
		// TODO Auto-generated method stub
		for (Vacante v : lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		}
		return null;
	}



	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
	}



	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

}
