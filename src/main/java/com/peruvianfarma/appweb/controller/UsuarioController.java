package com.peruvianfarma.appweb.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.peruvianfarma.appweb.model.Usuario;
import com.peruvianfarma.appweb.repository.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpServletRequest;

>>>>>>> origin/master
@Controller
public class UsuarioController {
    
    private static final String INDEX ="usuario/login"; 
    private static String MODEL_CONTACT="user";
    private static String MODEL_MESSAGE="mensaje";
    private final UsuarioRepository usuariosData;

    public UsuarioController(UsuarioRepository usuariosData){
        this.usuariosData = usuariosData;
    }      

    @GetMapping("/usuario/login")
    public String login(Model model) {
        model.addAttribute(MODEL_CONTACT, new Usuario());
        return INDEX;
    }  

    @PostMapping("/usuario/login")
    public String loginSubmitForm(Model model, 
<<<<<<< HEAD
         @Valid Usuario objUser, BindingResult result ){
=======
         @Valid Usuario objUser, 
         HttpServletRequest request,
         BindingResult result ){
>>>>>>> origin/master
        String page=INDEX;
        model.addAttribute(MODEL_CONTACT, new Usuario());
        if(result.hasFieldErrors()) {
            model.addAttribute(MODEL_MESSAGE, "No se ha podido loguear");
        }else{
            Optional<Usuario> userDB = this.usuariosData.findById(objUser.getUserID());
            if(userDB.isPresent()){
                if(userDB.get().getPassword().equals(objUser.getPassword())){
                    model.addAttribute(MODEL_CONTACT,userDB.get());
                    model.addAttribute(MODEL_MESSAGE, "Usuario existe");
<<<<<<< HEAD
=======
                    request.getSession().setAttribute("user", objUser);
>>>>>>> origin/master
                    page="welcome";  
                }else{
                    model.addAttribute(MODEL_MESSAGE, "Password no coincide");  
                }
            }else{
                model.addAttribute(MODEL_MESSAGE, "Usuario no existe");
            }
        }
        return page;
    }
<<<<<<< HEAD
=======

    @GetMapping("/usuario/logout")
	public String logoutSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}
>>>>>>> origin/master
}