package br.com.api.services.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

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
import br.com.api.model.MessageModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.UserResponse;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class LoginServiceImplTest {
	
	private static TestUtils UTILS;
	
	@Inject
	private LoginServiceImpl loginService;
	
	@Inject
	private MessageModel messageModel;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	@BeforeClass
	public static void setUp() {
		UTILS = new TestUtils();
	}

	@Test
	public void authenticateUserTest() {
		UserResponse response;
		
		UserEntity user = UTILS.generateOneOptionalUserEntity().get();
		String passwrodDecrypted = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
		user.setPassword(passwrodDecrypted);
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.of(user));
		
		response = loginService.authenticateUser(UTILS.generateOneOptionalUserEntity().get());
		
		assertThat(response.getUser().isPresent(), equalTo(true));
	}
	
	@Test
	public void authenticateInvalidUserTest() {
		UserResponse response;
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());
		
		response = loginService.authenticateUser(UTILS.generateOneOptionalUserEntity().get());
		
		assertThat(response.getUser().isPresent(), equalTo(false));
		assertThat(response.getMessageError().matches(messageModel.getWrongPasswordOrEmail()), equalTo(true));
	}
}
