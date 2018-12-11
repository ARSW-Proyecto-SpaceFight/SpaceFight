package edu.eci.arsw.spacefight.spacefight.mongodb;


import edu.eci.arsw.spacefight.spacefight.security.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {

    public ApplicationUser findByUsername(String username);
}