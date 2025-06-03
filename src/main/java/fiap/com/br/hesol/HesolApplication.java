package fiap.com.br.hesol;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Objects;
import java.util.Properties;

@EnableCaching
@EnableJpaRepositories
@ComponentScan
@EntityScan
@SpringBootApplication
public class HesolApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure()
				.directory(".")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		String dbUrl = dotenv.get("DB_URL");
		String dbUser = dotenv.get("DB_USERNAME");
		String dbPass = dotenv.get("DB_PASSWORD");

		if (dbUrl == null || dbUser == null || dbPass == null) {
			System.err.println("Variáveis do .env não carregadas corretamente.");
			System.err.println("DB_URL: " + dbUrl);
			System.err.println("DB_USER: " + dbUser);
			System.err.println("DB_PASS: " + dbPass);
			System.exit(1);
		}

		Properties props = new Properties();
		props.setProperty("spring.datasource.url", dbUrl);
		props.setProperty("spring.datasource.username", dbUser);
		props.setProperty("spring.datasource.password", dbPass);

		SpringApplication app = new SpringApplication(HesolApplication.class);
		app.setDefaultProperties(props);
		app.run(args);
	}
}
