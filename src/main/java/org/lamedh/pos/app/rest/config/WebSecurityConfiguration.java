package org.lamedh.pos.app.rest.config;

//import org.lamedh.pos.domain.employee.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Configuration
//class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//    @Autowired EmployeeService employeeService;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService());
//    }
//
//    UserDetailsService userDetailsService() {
//        return (code) -> employeeService.getByCode(code)
//                .map(e -> new User(e.getCode(), e.getCode() + "p", AuthorityUtils.createAuthorityList("USER", "write")))
//                .orElseThrow(() -> new UsernameNotFoundException("could not find the user '" + code + "'"));
//    }
//}