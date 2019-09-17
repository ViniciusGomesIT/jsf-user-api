package br.com.api.controller.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

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
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.services.interfaces.UserService;
import br.com.api.utils.ContextMocker;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserContollerTest {
	
	private static final String LIST_PAGE_PATH = "/list.xhtml";
	private static final String REGISTER_PAGE_PATH = "/register";
	private static final String EDIT_PASSWORD_PAGE_PATH = "/edit-password";
	
	private static TestUtils UTILS;
	
	@Inject
	private UserController userController;
	
	@Mock
	private UserService userServiceMock;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@BeforeClass
	public static void setUp() {
		UTILS = new TestUtils();
	}

	@Test
	public void saveUserTest() {
		String name = "Name Teste";
		String email = "email@email.com";
		String password = "123456";
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());
		
		given(userRepositoryMock.save(Mockito.any(UserEntity.class)))
			.willReturn(UTILS.generateOneOptionalUserEntity().get());
		
		when(userServiceMock.saveUser(Mockito.any(SaveUserRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		userController.getSaveUserRequest().setName(name);
		userController.getSaveUserRequest().setEmail(email);
		userController.getSaveUserRequest().setEmailConfirm(email);;
		userController.getSaveUserRequest().setPassword(password);
		userController.getSaveUserRequest().setPasswordConfirm(password);
		
		String response = userController.save();
		
		assertThat(response.contains(LIST_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void saveWithAlreadyUsedEmailTest() {
		ContextMocker.mockFacesContext();
		String email = "teste@email.com";
		String password = "123123";
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
	
		when(userServiceMock.saveUser(Mockito.any(SaveUserRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		userController.getSaveUserRequest().setEmail(email);
		userController.getSaveUserRequest().setEmailConfirm(email);
		userController.getSaveUserRequest().setPassword(password);
		
		String response = userController.save();
		
		assertThat(response.contains(REGISTER_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void findAllUsersTest() {
		ContextMocker.mockFacesContext();
		List<UserEntity> userList;
		
		given(userRepositoryMock.findAll())
			.willReturn(UTILS.generateOneOptionalUserEntityList());
		
		when(userServiceMock.saveUser(Mockito.any(SaveUserRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		userList = userController.findAllUsers();
		
		assertThat(userList.isEmpty(), equalTo(false));
	}
	
	@Test
	public void editUserTest() {
		ContextMocker.mockFacesContext();
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		when(userServiceMock.saveUser(Mockito.any(SaveUserRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		String response = userController.editUser(1L);
		
		assertThat(response.contains(REGISTER_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void editUserNotFoundTest() {
		ContextMocker.mockFacesContext();
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(Optional.empty());
		
		when(userServiceMock.saveUser(Mockito.any(SaveUserRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
			
		String response = userController.editUser(1L);
		
		assertThat(response.contains(LIST_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void editPasswordTest() {
		String response = userController.editPassword(1L);
		
		assertThat(response.contains(EDIT_PASSWORD_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void checkPasswordTest() {
		ContextMocker.mockFacesContext();
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		when(userServiceMock.checkAndUpdatePassword(Mockito.any(ResetPasswordRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		String response = userController.checkPassword();
		
		assertThat(response.contains(LIST_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void checkPasswordWithWrongPasswordTest() {
		ContextMocker.mockFacesContext();
		String oldPassword = "kkikikoskaso";
		String newPassword = "321654897";
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		when(userServiceMock.checkAndUpdatePassword(Mockito.any(ResetPasswordRequest.class)))
			.thenReturn(UTILS.generateOneUserResponse(true, null, null));
		
		userController.getPasswordRequest().setId(1L);
		userController.getPasswordRequest().setOldPassword(oldPassword);
		userController.getPasswordRequest().setNewPassword(newPassword);
		userController.getPasswordRequest().setNewPasswordConfirm(newPassword);
		
		String response = userController.checkPassword();
		
		assertThat(response.contains(EDIT_PASSWORD_PAGE_PATH), equalTo(true));
	}
	
	@Test
	public void addPhoneTest() {
		userController.getSaveUserRequest().setPhones(new ArrayList<>());
		
		userController.addPhone();
		
		assertThat(userController.getSaveUserRequest().getPhones().isEmpty(), equalTo(false));
	}
	
	@Test
	public void deletePhoneTest() {
		userController.getSaveUserRequest().setPhones(UTILS.generateOnePhoneEntityList());
		
		userController.deletePhone();
		
		assertThat(userController.getSaveUserRequest().getPhones().size(), equalTo(1));
	}
	
}
