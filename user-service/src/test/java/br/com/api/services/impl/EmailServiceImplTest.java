package br.com.api.services.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.model.MessageModel;
import br.com.api.repository.UserRepository;
import br.com.api.response.EmailSenderResponse;
import br.com.api.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "test")
public class EmailServiceImplTest {
	
	private static TestUtils UTILS;
	
	@Inject
	private EmailServiceImpl emailService;
	
	@Inject
	MessageModel messageModel;
	
	@MockBean
	private UserRepository userRespositoryMock;
	
	@Mock
	private JavaMailSender javaMailSenderMock;
	
	@BeforeClass
	public static void setUp() {
		UTILS = new TestUtils();
	}
	
	@Test
	public void sendEmailTest() {
		EmailSenderResponse response;
		String email = "email@email.com.br";
		
		BDDMockito.given(userRespositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(UTILS.generateOneOptionalUserEntity());
		
		doNothing().when(javaMailSenderMock).send(Mockito.any(SimpleMailMessage.class));
		
		response = emailService.sendEmail(email);
		
		assertThat(response.getIsEmailSent(), equalTo(true));
		assertThat(response.getMessageError().isEmpty(), equalTo(true));
		
	}
	
	@Test
	public void sendEmailToInvalidEmailTest() {
		EmailSenderResponse response;
		String email = "email@email.com.br";
		
		BDDMockito.given(userRespositoryMock.findByEmailIgnoreCase(Mockito.anyString()))
			.willReturn(Optional.empty());
		
		doNothing().when(javaMailSenderMock).send(Mockito.any(SimpleMailMessage.class));
		
		response = emailService.sendEmail(email);
		
		assertThat(response.getIsEmailSent(), equalTo(false));
		assertThat(response.getMessageError().isEmpty(), equalTo(false));
		assertThat(response.getMessageError().matches(messageModel.getEmailSendError()), equalTo(true));
	}

}
