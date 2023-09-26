package br.com.Iticket.project.service;


import br.com.Iticket.project.model.User;
import br.com.Iticket.project.repository.UserRepository;
import br.com.Iticket.project.service.exceptions.ForbiddenException;
import br.com.Iticket.project.service.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public User authenticated() {
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return repository.findByEmail(username);
        }
        catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();

        if (!user.getId().equals(userId) && !user.getRole().getAuthority().equals("ROLE_ADMIN")) {
            throw new ForbiddenException("Acces denied");
        }
    }

}
