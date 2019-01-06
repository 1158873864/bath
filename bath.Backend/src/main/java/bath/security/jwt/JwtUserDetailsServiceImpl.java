package bath.security.jwt;

import bath.data.dao.admin.AdminDao;
import bath.data.dao.user.UserDao;
import bath.entity.admin.Admin;
import bath.entity.user.User;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
    private final UserDao userDao;
    private final AdminDao adminDao;

    private final JwtService jwtService;

    @Autowired
    public JwtUserDetailsServiceImpl(UserDao userDao, AdminDao adminDao, JwtService jwtService) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.jwtService = jwtService;
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findById(username); //用户的username为openid
        if (optionalUser.isPresent()) {
            System.out.println("isPresent");
            return new JwtUser(optionalUser.get().getOpenid(), "", Collections.singletonList(JwtRole.USER));
        } else {
            List<Admin> admins = adminDao.findAdminByUsername(username);
            if (!admins.isEmpty()) {
                Admin admin = admins.get(0);
                return new JwtUser(admin.getUsername(), admin.getPassword(), Collections.singletonList(JwtRole.ADMIN));
            } else {
                throw new UsernameNotFoundException("username " + username + " not found");
            }
        }
        /*
         * edit by zlz
         * 2018-9-3
         */
//        List<User> users = userDao.findUserByUsername(username);
//        List<Admin> admins = adminDao.findAdminByUsername(username);
//        if (users.isEmpty() && admins.isEmpty()) {
//            throw new UsernameNotFoundException("username not found");
//        } else {
//            if (!users.isEmpty()) {
//                User user = users.get(0);
//                List<JwtRole> jwtRoles = new ArrayList<>();
//                jwtRoles.add(JwtRole.USER);
//                JwtUser jwtUser = new JwtUser(user.getUsername(), "", jwtRoles);
//                return jwtUser;
//            } else {
//                Admin user = admins.get(0);
//                List<JwtRole> jwtRoles = new ArrayList<>();
//                jwtRoles.add(JwtRole.ADMIN);
//                JwtUser jwtUser = new JwtUser(user.getUsername(), "", jwtRoles);
//                return jwtUser;
//            }
//        }
    }
}
