package edu.eci.arsw.spacefight.spacefight.restcontrollers;



import edu.eci.arsw.spacefight.spacefight.mongodb.UserRepository;
import edu.eci.arsw.spacefight.spacefight.security.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

        @Autowired
        private UserRepository applicationUserRepository;
        @Autowired
        private PasswordEncoder bCryptPasswordEncoder;

        @RequestMapping(value ="/sign-up",method = RequestMethod.POST)
        public ResponseEntity<?> signUp(@RequestBody ApplicationUser user) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                applicationUserRepository.save(user);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }

        @RequestMapping(value = "/prueba", method = RequestMethod.GET)
        public ResponseEntity<?> prueba(){
            return new ResponseEntity<>("blaaaaa", HttpStatus.ACCEPTED);
        }
    }

