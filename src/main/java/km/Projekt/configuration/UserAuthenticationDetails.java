package km.Projekt.configuration;

import km.Projekt.dao.UserDao;
import km.Projekt.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import
        org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import
        org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class UserAuthenticationDetails implements UserDetailsService {
    @Autowired
    private UserDao dao;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = dao.findByLogin(login);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("normalUser"));

            // Wrap the UserDetails with the decorator
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), true, true, true, true, authorities);

            return new UserDetailsDecorator(userDetails);
        } else {
            throw new UsernameNotFoundException("Invalid login or password.");
        }
    }
}