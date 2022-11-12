package com.nttdata.bootcamp.msaccounts;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	
	@Test
	public void getByAccount() {
		Map<String, String>params= Collections.singletonMap("nroAccount", "0201017455548278832770428400871002639623");
		client.get()
		.uri("/{nroAccount}",params)
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBody(Account.class);
	}

}
