package saga_impl_example.saga_impl_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.agt.orchestrated.saga.config.SagaConfig;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import(SagaConfig.class)
public class SagaImplExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SagaImplExampleApplication.class, args);
	}

}
