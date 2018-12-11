package edu.eci.arsw.spacefight.spacefight.security;


import org.springframework.data.annotation.Id;

public class ApplicationUser {


        @Id
        private String username;
        private String password;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

