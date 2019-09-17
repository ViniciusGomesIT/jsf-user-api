package br.com.api.controller.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Base64;
import java.util.Optional;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.entity.UserEntity;
import br.com.api.repository.UserRepository;
import br.com.api.services.interfaces.LoginService;
import br.com.api.utils.ContextMocker;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoginControllerTest {
	
	private static final String LIST_PAGE_PATH = "/list.xhtml";
	private static final String LOGIN_PAGE_PATH = "/login.xhtml";
	
	private static TestUtils UTILS;
	
	@Inject
	private LoginController loginController;
	
	@Mock
	private LoginService loginServiceMock;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@BeforeClass
	public static void setUp() {
		UTILS = new TestUtils();
	}
	
	@Test
	public void authenticateUserTest() {
		FacesContext context = ContextMocker.mockFacesContext();
		
		ExternalContext ext = Mockito.mock(ExternalContext.class);
		HttpSession session = Mockito.mock(HttpSession.class);
		
		when(ext.getSession(Mockito.anyBoolean()))
			.thenReturn(session);
		when(context.getExternalContext())
			.thenReturn(ext);
		
		String email = "email@email.com";
		String password = "somePassword";
		String passwordEncrypted = Base64.getEncoder().encodeToString(password.getBytes());
		
		loginController.getUser().setEmail(email);
		loginController.getUser().setPassword(password);
		
		UserEntity userEntity = UTILS.generateOneOptionalUserEntity().get();
		userEntity.setPassword(passwordEncrypted);
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.of(userEntity));
		
		when(loginServiceMock.authenticateUser(Mockito.any(UserEntity.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		String response = loginController.authenticateUser();
		
		assertThat(response.contains(LIST_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void authenticateInvalidUserTest() {
		ContextMocker.mockFacesContext();
		
		String email = "email@email.com";
		String password = "password";
		
		loginController.getUser().setEmail(email);
		loginController.getUser().setPassword(password);
		
		when(loginServiceMock.authenticateUser(Mockito.any(UserEntity.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());
		
		String response = loginController.authenticateUser();
		
		assertThat(response.contains(LOGIN_PAGE_PATH), equalTo(true));
	}
}
