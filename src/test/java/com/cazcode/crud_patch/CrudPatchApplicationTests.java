package com.cazcode.crud_patch;

import com.cazcode.crud_patch.dto.Cliente;
import com.cazcode.crud_patch.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CrudPatchApplicationTests {

	public static final String JOHN_DOE = "John Doe";
	public static final String PHONE = "312-1234567";
	public static final String MAIL = "john.doe@gmail.com";

	@Test
	void contextLoads() {
	}

	@Test
	public void mapperClientToCliente() {
		//given
		Client client = new Client();
		client.setName(JOHN_DOE);
		client.setPhone(PHONE);
		client.setEmail(MAIL);

		//when
		Cliente cliente = ClientMapper.INSTANCE.toCliente(client);

		//then
		assertThat( cliente ).isNotNull();
		assertThat( cliente.name()).isEqualTo( JOHN_DOE );
		assertThat( cliente.phone()).isEqualTo( PHONE );
		assertThat( cliente.email()).isEqualTo( MAIL);

	}

}
