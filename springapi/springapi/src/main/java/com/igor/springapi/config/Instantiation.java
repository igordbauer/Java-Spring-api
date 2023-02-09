package com.igor.springapi.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.igor.springapi.DTO.AuthorDTO;
import com.igor.springapi.DTO.CommentDTO;
import com.igor.springapi.domain.Post;
import com.igor.springapi.domain.User;
import com.igor.springapi.repository.PostRepository;
import com.igor.springapi.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(
                null,
                sdf.parse("09/02/2023"),
                "Bom dia",
                "Muito trabalho pra fazer hoje",
                new AuthorDTO(maria));
        Post post2 = new Post(
                null,
                sdf.parse("03/02/2023"),
                "Estrada cheia",
                "Muito movimento nas estradas esse final de semana",
                new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Ninguem te perguntou", sdf.parse("09/02/2023"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Que legal!", sdf.parse("09/02/2023"), new AuthorDTO(alex));
        CommentDTO c3 = new CommentDTO("Que legal!", sdf.parse("09/02/2023"), new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));
        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }

}
