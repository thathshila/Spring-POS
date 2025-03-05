package lk.ijse.spring_pos.service;


import lk.ijse.spring_pos.dto.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
}