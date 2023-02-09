package com.igor.springapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.springapi.DTO.UserDTO;
import com.igor.springapi.domain.User;
import com.igor.springapi.repository.UserRepository;
import com.igor.springapi.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repo.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        Optional<User> newObjRepo = repo.findById(obj.getId());
        updateData(newObjRepo, obj);
        return repo.save(fromOptional(newObjRepo));
    }

    private void updateData(Optional<User> newObjRepo, User obj) {
        newObjRepo.get().setName(obj.getName());
        newObjRepo.get().setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public User fromOptional(Optional<User> obj) {
        return new User(obj.get().getId(), obj.get().getName(), obj.get().getEmail());
    }

}
