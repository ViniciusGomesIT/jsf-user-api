package br.com.api.services.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.Base64;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.entity.UserEntity;
import br.com.api.repository.UserRepository;
import br.com.api.request.ResetPasswordRequest;
import br.com.api.request.SaveUserRequest;
import br.com.api.response.UserListResponse;
import br.com.api.response.UserResponse;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {
	private static TestUtils UTILS;
	
	@Inject
	private UserServiceImpl userService;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@BeforeClass
	public static void setUp() {
		UTILS = new TestUtils();
	}

	@Test
	public void saveUserTest() {
		UserResponse response;
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());
		
		given(userRepositoryMock.save(Mockito.any(UserEntity.class)))
			.willReturn(UTILS.generateOneOptionalUserEntity().get());
		
		response = userService.saveUser(UTILS.generateOneSaveUserRequest(false));
		
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
	}
	
	@Test
	public void saveUserWithEmailAlreadyUsedTest() {
		UserResponse response;
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		response = userService.saveUser(UTILS.generateOneSaveUserRequest(false));
		
		assertThat(response.getMessageError().isEmpty(), equalTo(false));
	}
	
	@Test
	public void updateUserTest() {
		UserResponse response;
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		given(userRepositoryMock.save(Mockito.any(UserEntity.class)))
			.willReturn(UTILS.generateOneOptionalUserEntity().get());
		
		response = userService.saveUser(UTILS.generateOneSaveUserRequest(true));
		
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
	}
	
	@Test
	public void updateWithEmailAlreadyUsedTest() {
		UserResponse response;
		String email = "testeWithAlreadyUsedEmail@email.com";
		
		UserEntity userEntity = UTILS.generateOneOptionalUserEntity().get();
		userEntity.setEmail(email);
		
		SaveUserRequest request = UTILS.generateOneSaveUserRequest(true);
		request.setEmail(email);
		request.setEmailConfirm(email);
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(Optional.of(userEntity));
		
		response = userService.saveUser(UTILS.generateOneSaveUserRequest(true));
		
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
	}
	
	@Test
	public void findAllUsersTest() {
		UserListResponse response;
		
		given(userRepositoryMock.findAll())
			.willReturn(UTILS.generateOneOptionalUserEntityList());
		
		response = userService.findAllUsers();
		
		assertThat(response.getUserList().isEmpty(), equalTo(false));
	}
	
	@Test
	public void deleteByIdTest() {
		
		doNothing().when(userRepositoryMock).deleteById(Mockito.anyLong());
		
		userService.deleteById(1L);
		
		verify(userRepositoryMock, timeout(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	public void checkAndUpdatePasswordTest() {
		UserResponse response;
		String oldPassword = "132456";
		String newPassword = "789456789456";
		
		ResetPasswordRequest request = new ResetPasswordRequest();
		request.setId(1L);
		request.setOldPassword(oldPassword);
		request.setNewPassword(newPassword);
		request.setNewPasswordConfirm(newPassword);
		
		UserEntity user = UTILS.generateOneOptionalUserEntity().get();
		user.setPassword(Base64.getEncoder().encodeToString(oldPassword.getBytes()));
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(Optional.of(user));
		
		response = userService.checkAndUpdatePassword(request);
		
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
	}
	
	@Test
	public void checkAndUpdatePasswordWithIncorrectOldPasswordTest() {
		UserResponse response;
		String oldPassword = "132456";
		String newPassword = "789456789456";
		
		ResetPasswordRequest request = new ResetPasswordRequest();
		request.setOldPassword(oldPassword);
		request.setNewPassword(newPassword);
		request.setNewPasswordConfirm(newPassword);
		
		given(userRepositoryMock.findById(Mockito.anyLong()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		response = userService.checkAndUpdatePassword(request);
		
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
	}
	
}
