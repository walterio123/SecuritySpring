package com.walterio12345.springwithjwtandtoken;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@SpringBootApplication
public class SpringWithJwtAndTokenApplication  {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringWithJwtAndTokenApplication.class, args);
    }




   // @Override
    //public void run(String... args) throws Exception {


       /*userService.saveRole(new Role(null,"ROLE_USER"));
        userService.saveRole(new Role(null,"ROLE_MANAGER"));
        userService.saveRole(new Role(null,"ROLE_ADMIN"));
        userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

        userService.saveUser(new User(null,"Juan Carlos","juan","1234",new ArrayList<>()));
        userService.saveUser(new User(null,"Pedro Alfonso","pedro","1234",new ArrayList<>()));
        userService.saveUser(new User(null,"Andres Calamaro","andrelo","1234",new ArrayList<>()));
        userService.saveUser(new User(null,"Ines de la Cruz","ines","1234",new ArrayList<>()));*/

        //userService.addRoleToUser("Juan Carlos","ROLE_USER");
        //userService.addRoleToUser("Pedro Alfonso","ROLE_MANAGER");
        //userService.addRoleToUser("Andres Calamaro","ROLE_ADMIN");
        //userService.addRoleToUser("Ines de la Cruz","ROLE_SUPER_ADMIN");

    //}
}
