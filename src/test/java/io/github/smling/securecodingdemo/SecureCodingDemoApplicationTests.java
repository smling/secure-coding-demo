package io.github.smling.securecodingdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(initializers = SecureCodingDemoApplicationTestInitializer.class)
class SecureCodingDemoApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(()->{
			SecureCodingDemoApplication.main(new String[]{});
		});
	}
}
