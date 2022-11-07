package br.com.g6.organizadorfinanceiro.applicationSecurity;

import br.com.g6.organizadorfinanceiro.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    public UserDetailsImpl(User user) {
        this.userName = user.getUserEmail();
        this.password = user.getUserPassword();
    }

    public UserDetailsImpl() {
    }

    @Override
    //GrantedAuthoritys are high level permissions the user is granted. A few examples are roles or scopes.
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
