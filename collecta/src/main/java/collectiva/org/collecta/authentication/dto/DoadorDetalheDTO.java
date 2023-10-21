package collectiva.org.collecta.authentication.dto;

import collectiva.org.collecta.domain.Doador;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class DoadorDetalheDTO implements UserDetails {
    @NotBlank(message = "O email esta vazio")
    private String email;

    @NotBlank(message = "A senha está vazia")
    private String senha;

    public DoadorDetalheDTO(Doador doador) {
        this.email = doador.getEmail();
        this.senha = doador.getSenha();
        this.nome = doador.getNome();
    }

    @NotBlank(message = "O nome esta vazio")
    private String nome;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
