package com.nttdata.bootcamp.msaccounts;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.bootcamp.msaccounts.model.Account;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsAccountApplicationTests {
	
	@Autowired
	private WebTestClient client;

	@Test
	public void getAccounts() {
		client.get()
		.uri("/")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Account.class)
		.consumeWith(resp->{
			List<Account> accounts=resp.getResponseBody();
			accounts.forEach(a->{
				System.out.println(a.toString());
			});
			
			Assertions.assertThat(accounts.size()>0).isTrue();
		});
	}

}
