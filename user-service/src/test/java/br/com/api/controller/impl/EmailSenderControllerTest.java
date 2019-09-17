package br.com.api.controller.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.repository.UserRepository;
import br.com.api.services.interfaces.EmailService;
import br.com.api.utils.ContextMocker;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmailSenderControllerTest {
	
	private static final String LOGIN_PAGE_PATH = "/login.xhtml";
	private static final String REMINDER_PASSWORD_PAGE_PATH = "/reminder-password.xhtml";

	private static TestUtils UTILS;

	@Inject
	private EmailSenderController emailSenderController;

	@Mock
	private EmailService serviceMock;
	
	@Mock
	private JavaMailSender mailSenderMock; 

	@MockBean
	private UserRepository userRepositoryMock;

	@BeforeClass
	public static void setUup() {
		UTILS = new TestUtils();
	}

	@Test
	public void emailSendTest() {
		ContextMocker.mockFacesContext();
		
		String email = "teste@email.com.br";
		emailSenderController.setEmail(email);
		
		doNothing().when(mailSenderMock).send(Mockito.any(SimpleMailMessage.class));
		
		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		when(serviceMock.sendEmail(Mockito.anyString()))
			.thenReturn(UTILS.generateOneEmailSenderResponse(true, null, null));

		String responseString = emailSenderController.sendEmail();

		assertThat(responseString.contains(LOGIN_PAGE_PATH), equalTo(true));
	}

	@Test
	public void emailSendWithoutValidEmailTest() {
		ContextMocker.mockFacesContext();
		
		String error = "Some error message!";
		
		String email = "teste@email.com.br";
		emailSenderController.setEmail(email);
		
		doNothing().when(mailSenderMock).send(Mockito.any(SimpleMailMessage.class));
		
		when(serviceMock.sendEmail(Mockito.anyString()))
			.thenReturn(UTILS.generateOneEmailSenderResponse(false, error, null));

		given(userRepositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());

		String responseString = emailSenderController.sendEmail();

		assertThat(responseString.contains(REMINDER_PASSWORD_PAGE_PATH), equalTo(true));
	}
}
