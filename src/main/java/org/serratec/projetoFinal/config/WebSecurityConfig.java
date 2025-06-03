package org.serratec.projetoFinal.config;

import java.util.Arrays;

import org.serratec.projetoFinal.config.security.JwtAuthenticationFilter;
import org.serratec.projetoFinal.config.security.JwtAuthorizationFilter;
import org.serratec.projetoFinal.config.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationsource() {
        CorsConfiguration corsConfiguraion = new CorsConfiguration();
        corsConfiguraion.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        corsConfiguraion.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguraion.applyPermitDefaultValues());
        return source;
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .cors((cors) -> cors.configurationSource(corsConfigurationsource()))
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(authorize -> 
            authorize
                .requestMatchers(HttpMethod.GET, "/clientes").permitAll()
                .requestMatchers(HttpMethod.POST, "/clientes").permitAll()
                .requestMatchers(HttpMethod.GET, "/categorias/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes/verMeusDados/me").permitAll()
                .requestMatchers(HttpMethod.POST, "/funcionarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/funcionarios").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/clientes/deletarConta/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/clientes/atualizarDados/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.POST, "/clientes/inserirEndereco/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.POST, "/produtos/inserirProdutos").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.POST, "/funcionarios/inserirFuncionario").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.POST, "/categorias/inserirCategoria").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.DELETE, "/funcionarios/deletarFuncinario/{id}").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/clientes/me/deletarEndereco/{id}").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.POST, "/clientes/inserirPedido/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/login/historico").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes/me/listarPedidoId/{id}").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/clientes/atualizarEndereco/me").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.GET, "/funcionarios/listarPedidos").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.POST, "/clientes/me/cancelarPedido/{id}").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.POST, "/clientes/me/marcarPedidoEntregue/{id}").hasRole("CLIENTE")
                .requestMatchers(HttpMethod.PUT, "/funcionarios/editarStatusPedido/{id}").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.GET, "/funcionarios/listarCliente/{id}").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.GET, "/funcionarios/pedidos/excel").hasRole("FUNCIONARIO")
                .requestMatchers(HttpMethod.GET, "/produtos/buscarPorValor").permitAll()
                .requestMatchers(HttpMethod.GET, "/produtos/buscarPorNome").permitAll()
                .requestMatchers(HttpMethod.GET, "/clientes/me/listarEndereco").hasRole("CLIENTE")
               
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html").permitAll() //teste
                
                //mudar permissôes depois
                
               //.requestMatchers(HttpMethod.GET, "/funcionarios/nome").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
        )
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers(headers -> headers.frameOptions().disable()); // configuração para o h2
        
        JwtAuthenticationFilter jwtAuthenticationFilter = 
                new JwtAuthenticationFilter(authenticationManager(
                        http.getSharedObject(AuthenticationConfiguration.class)),
                        jwtUtil);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        JwtAuthorizationFilter jwtAuthorizationFilter = 
                new JwtAuthorizationFilter(authenticationManager(
                        http.getSharedObject(AuthenticationConfiguration.class)), 
                        jwtUtil, 
                        userDetailsService);

        http.addFilter(jwtAuthenticationFilter);
        http.addFilter(jwtAuthorizationFilter);

        return http.build();
    }
	

}
