package br.ifpr.agenda.controllers;

import java.util.Optional;

import javax.validation.Valid;
import br.ifpr.agenda.dominio.Usuario;
import br.ifpr.agenda.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/agenda/usuarios/cadastrar")
    public ModelAndView cadastrar(Usuario usuario) {
        ModelAndView mv = new ModelAndView("agenda/usuarios/cadastro");
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/agenda/usuarios/listar")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("agenda/usuarios/lista");
        mv.addObject("listaUsuarios", usuarioRepository.findAll());
        return mv;
    }

    @GetMapping("/agenda/usuarios/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return cadastrar(usuario.get());
    }

    @GetMapping("/agenda/usuarios/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario.get());
        return listar();
    }

    @PostMapping("/agenda/usuarios/salvar")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(usuario);
        }
        usuarioRepository.saveAndFlush(usuario);
        return cadastrar(new Usuario());
    }
}
