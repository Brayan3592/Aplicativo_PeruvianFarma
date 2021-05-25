package com.peruvianfarma.appweb.controller;


import java.util.List;
import java.util.Optional;

import com.peruvianfarma.appweb.model.Producto;
import com.peruvianfarma.appweb.model.Proforma;
import com.peruvianfarma.appweb.model.Usuario;
import com.peruvianfarma.appweb.repository.ProductoRepository;
import com.peruvianfarma.appweb.repository.ProformaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.peruvianfarma.appweb.repository.UsuarioRepository;

@Controller
public class CatalogoController{

    private static final String INDEX ="catalogo/index"; 
    private final ProductoRepository productsData;
    private final ProformaRepository proformaData;
    private final UsuarioRepository usuariosData;
    

    public CatalogoController(ProductoRepository productsData,
        ProformaRepository proformaData, UsuarioRepository usuariosData
        ){
        this.productsData = productsData;
        this.proformaData = proformaData; 
        this.usuariosData = usuariosData;
        
    }      

    @GetMapping("/catalogo/index")
    public String index(
        @RequestParam(defaultValue="") String searchName,
        Model model){
        List<Producto> listProducto = this.productsData.getAllActiveProductos();
        model.addAttribute("products",listProducto);
        return INDEX;
    }    

    @GetMapping("/catalogo/add/{id}")
    public String add(@PathVariable("id") Integer id, 
        HttpSession session,
        Model model, @Valid Usuario objUser, BindingResult result){
        Usuario user = (Usuario)session.getAttribute("user");
        if(user==null) {
            model.addAttribute("mensaje", "Debe loguearse antes de agregar");
        }else{
            Producto productSeleccionado = productsData.getOne(id);
            Optional<Proforma> item= 
                proformaData.findProformaByUsuarioAndProducto(user, productSeleccionado);
            if(!item.isPresent()){
                Proforma itemCarrito = new Proforma();
                itemCarrito.setCantidad(1);
                itemCarrito.setUser(user);
                itemCarrito.setPrecio(productSeleccionado.getPrecio());
                itemCarrito.setProduct(productSeleccionado);
                proformaData.save(itemCarrito);
                model.addAttribute("mensaje", "Se agrego el producto al carrito");
            }else{
                Proforma itemCarritoExistente=item.get();
                itemCarritoExistente.setCantidad(itemCarritoExistente.getCantidad()+1);
                proformaData.save(itemCarritoExistente);
                model.addAttribute("mensaje", "Se adiciono el producto al carrito");
            }
        }   
        return INDEX;
    }  
}