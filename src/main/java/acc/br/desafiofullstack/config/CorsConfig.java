package acc.br.desafiofullstack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173") // Adicione os domínios permitidos separados por vírgula
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Defina os métodos HTTP permitidos
            .allowedHeaders("*") // Defina os cabeçalhos permitidos
            .allowCredentials(true); // Permita envio de cookies e autenticação via cabeçalho Authorization
    }
}

