package br.com.api.controller.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.api.controller.model.MessageModel;
import br.com.api.controller.resource.UserResource;
import br.com.api.entity.PhoneEntity;
import br.com.api.entity.UserEntity;
import br.com.api.enums.PhoneTypeEnum;
import br.com.api.repository.UserRepository;
import br.com.api.utils.security.GenerateMD5;

@Scope(value = "session")
@Component(value = "userController")
@ELBeanName(value = "userController")
@Join(path = "/user", to = "/user-form.jsf")
public class UserController implements UserResource {

	private UserRepository userRepository;
	private MessageModel message;
	
	private UserEntity user;
	
	FacesContext context;
	
	@Inject
	public UserController(UserRepository userRepository, MessageModel message) {
		this.userRepository = userRepository;
		this.message = message;
		this.user = new UserEntity();
		this.context = FacesContext.getCurrentInstance();
	}

	@Override
	public String save() {
		Optional<UserEntity> userOpt = userRepository.findByEmailIgnoreCase(user.getEmail());
		
		if ( userOpt.isPresent() ) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message.getUserAlreadyRegistered(), message.getGenericError()));
			
			return null;
		}
		
		user.setPassword(GenerateMD5.generate(user.getPassword()));
		
		PhoneEntity phone = new PhoneEntity();
		phone.setDdd(81);
		phone.setNumber("12345678");
		phone.setType(PhoneTypeEnum.PESSOAL);
		
		user.setPhones(Arrays.asList(phone));
		
		userRepository.save(user);
		
		return "/product-list.xhtml?faces-redirect=true";
	}

	public UserEntity getUser() {
		return user;
	}

	@Override
	public List<UserEntity> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
}
