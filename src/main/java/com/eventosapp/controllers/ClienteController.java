package com.eventosapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Cliente;
import com.eventosapp.models.Convidado;
import com.eventosapp.models.Endereco;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ClienteRepository;
import com.eventosapp.repository.EnderecoRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public ClienteController(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
	}

	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.GET)
	public String form() {
		return "cliente/formCliente";
	}

	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(@Valid Cliente cliente, Endereco endereco, BindingResult result, RedirectAttributes attributes) {
		Endereco enderecoCadastro = new Endereco();
		Cliente clienteCadastro = new Cliente();
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarCliente";
		}

		clienteCadastro = this.clienteRepository.save(cliente);

		enderecoCadastro.setIdCliente(clienteCadastro.getId());
		enderecoCadastro.setLogradouro(endereco.getLogradouro());
		enderecoCadastro.setNumero(endereco.getNumero());
		enderecoCadastro.setCidade(endereco.getCidade());
		enderecoCadastro.setEstado(endereco.getEstado());
		enderecoCadastro.setPais(endereco.getPais());

		this.enderecoRepository.save(enderecoCadastro);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastrarCliente";
	}

	@RequestMapping("/clientes")
	public ModelAndView listaClientes() {
		ModelAndView mv = new ModelAndView("listaClientes");
		Iterable<Cliente> clientes = this.clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}
		
	@RequestMapping(value = "/editarCliente/{id}", method = RequestMethod.GET)
	public ModelAndView editarCliente(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("cliente/editarClientes");
		Endereco endereco = new Endereco();
		Cliente cliente = this.clienteRepository.findOne(id);
		endereco = this.enderecoRepository.findEnderecoByIdCliente(cliente.getId());
		mv.addObject("cliente", cliente);
		mv.addObject("endereco", endereco);
		return mv;
	}
	
	/*
	 * @PostMapping(value = "/editarCliente") public String editarClientePut(@Valid
	 * Cliente cliente, BindingResult result, RedirectAttributes attributes) {
	 * 
	 * Cliente clienteCadastro = new Cliente(); if (result.hasErrors()) {
	 * attributes.addFlashAttribute("mensagem", "Verifique os campos!"); return
	 * "redirect:/cadastrarCliente"; }
	 * 
	 * clienteCadastro = this.clienteRepository.save(cliente);
	 * 
	 * attributes.addFlashAttribute("mensagem", "Cliente editado com sucesso!");
	 * return "redirect:/editartarCliente"; }
	 */
	
	@PostMapping("/editarCliente/{id}")
	public String editarClientePut(@PathVariable("id") Long id, @Valid Cliente cliente, 
	  BindingResult result, Model model) {
		Cliente clienteCadastro = new Cliente();
		ModelAndView mv = new ModelAndView("listaClientes");
		ModelAndView mvCliente = new ModelAndView("editarCliente");
	    if (result.hasErrors()) {
	    	cliente.setId(id);
	        return "editarCliente";
	    }
	         
	    clienteCadastro = this.clienteRepository.save(cliente);
	    Iterable<Cliente> clientes = this.clienteRepository.findAll();
	    model.addAttribute("clientes", clientes);
	    return "redirect:/clientes";
	}
	
	/**
	 * */
	@RequestMapping(value = "/detalheCliente/{id}", method = RequestMethod.GET)
	public ModelAndView detalheCliente(@PathVariable("id") Long id) {
		Cliente cliente = this.clienteRepository.findOne(id);
		ModelAndView mv = new ModelAndView("cliente/detalheClientes");
		mv.addObject("cliente", cliente);

		Endereco endereco = this.enderecoRepository.findEnderecoByIdCliente(cliente.getId());
		mv.addObject("endereco", endereco);

		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesClientePost(@PathVariable("id") Long id, @Valid Cliente cliente, BindingResult result,
			RedirectAttributes attributes) {
		Endereco enderecoDetalhe = new Endereco();
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		}
		Cliente clienteDetalhe = this.clienteRepository.findOne(id);
		enderecoDetalhe = this.enderecoRepository.findEnderecoByIdCliente(clienteDetalhe.getId());
//		convidado.setEvento(evento);
	//	cr.save(convidado);
//		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{id}";
	}

	@RequestMapping(value = "/deletarCliente/{id}", method = RequestMethod.GET)
	public String deletarCliente(@PathVariable("id") Long id) {
		
		  Cliente cliente = this.clienteRepository.findOne(id); 
		  Endereco endereco = this.enderecoRepository.findEnderecoByIdCliente(cliente.getId());
		  this.clienteRepository.delete(cliente);
		  this.enderecoRepository.delete(endereco);
		 
		return "redirect:/clientes";
	}
}
