package com.danromlop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danromlop.model.Categoria;
import com.danromlop.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;

	//@GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", lista);
		
		return "categorias/listCategorias";
	}
	
	//@GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		
		
		return "categorias/formCategoria";
	}
	
	//@PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
	
		if(result.hasErrors()) {
			
			for (ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			return "/categorias/formVacante";
		}
		
		serviceCategorias.guardar(categoria);
		System.out.println("Categoria: " + categoria);
		attributes.addFlashAttribute("msg", "Categoria guardada");
		
		return "redirect:/categorias/index";
	}
}
