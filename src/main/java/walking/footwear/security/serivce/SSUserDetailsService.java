package walking.footwear.security.serivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import walking.footwear.dao.UserRepository;
import walking.footwear.model.User;

import javax.transaction.Transactional;

@Transactional
@Service
public class SSUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public SSUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User appUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            System.out.println("User from username " + appUser.getUsername());
            return UserDetailsImpl.build(appUser);

            //return new CustomerUserDetails(appUser, getAuthorities(appUser));

    }

//    private Set<GrantedAuthority> getAuthorities(User appUser) {
//        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        for (Role role : appUser.getRoles()) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole().toString());
//            authorities.add(grantedAuthority);
//        }
//        System.out.println("User authorities are" + authorities.toString());
//        return authorities;
//    }
}
