package com.danromlop.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.danromlop.model.Vacante;
import com.danromlop.service.ICategoriasService;
import com.danromlop.service.IVacantesService;
import com.danromlop.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	//inyeccion de ruta declarada en application.properties a través de la anotacion @Value
	@Value("${empleosapp.ruta.imagenes}") //ruta almacenada en application.properties
	private String ruta;
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired	
	@Qualifier("categoriasServiceJpa")
	private ICategoriasService serviceCategorias;
	
	@GetMapping("/index")
		public String mostrarIndex(Model model) {
		
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		
		return "vacantes/listVacantes";
		}
	
	
	@GetMapping("/create")
	public String crear( Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategorias.buscarTodas());
		
		return "vacantes/formVacante";
	}
	
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart ) {
		
		if(result.hasErrors()) {
			
			for (ObjectError error: result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			System.out.println(vacante);
			
			return "vacantes/formVacante";
		}
		
		/* añadiendo imagen */
		if(!multiPart.isEmpty()) {
			//String ruta = "d:/empleos/img-vacantes/"; // tenemos ruta en application.properties
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta); //ruta declarada al principio de la clase
			if(nombreImagen != null) {
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		
		
		
		serviceVacantes.guardar(vacante);
		System.out.println("Vacante: " + vacante);
		//usamos RedirectAttributes (flash attributes) para enviar datos a una peticion HTTP diferente del método actual (redirect)
		attributes.addFlashAttribute("msg", "Registro guardado"); 
		//redirect - para que se retorne al listado de ofertas a traves del metodo mostrarIndex, mostrando los datos a traves del model del mismo metodo
		return "redirect:/vacantes/index";
	}
	
	
	/*metodo antiguo
	@PostMapping("/save")
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("estatus") String estatus,
				@RequestParam("fecha") String fecha, @RequestParam("destacado") int destacado, @RequestParam("salario") double salario,
				@RequestParam("detalles") String detalles) {
		System.out.println("Nombre Vacante: " + nombre);
		System.out.println("Descripcion: " + descripcion);
		System.out.println("Estatus: " + estatus);
		System.out.println("Fecha Publicación: " + fecha);
		System.out.println("Destacado : " + destacado);
		System.out.println("Salario ofrecido : " + salario);
		System.out.println("Detalles  : " + detalles);
		
		
		return "vacantes/listVacantes";
	}
	*/
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con id " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		
		System.out.println("IdVacante: " + vacante);
		model.addAttribute("vacante", vacante);
		return "detalle";
		
		
	}
	//metodo para realizar la conversion del tipo dato del formulario al tipo de dato del objeto Vacante
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
