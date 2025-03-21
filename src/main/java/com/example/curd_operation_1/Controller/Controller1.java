package com.example.curd_operation_1.Controller;

import com.example.curd_operation_1.Entity.UserData;
import com.example.curd_operation_1.Repository.UserDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/crud")
public class Controller1 {

    private UserDataRepository userDataRepository;

    public Controller1(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }



    @PostMapping("/NewUser")
    public UserData addNewUser(
            @RequestBody UserData userData
            ){
        return userDataRepository.save(userData);
    }


    @GetMapping("/getDetails")
    public Optional<UserData> getDetailsByMobile(
            @RequestParam String Mobile
    ){
        return userDataRepository.findByMobile(Mobile);
    }


    @GetMapping("/getAll")
    public List<UserData> getAllUsers(){
        return userDataRepository.findAll();
    }


    @PutMapping("/updateEmail")
    public ResponseEntity<UserData> updateEmail(
            @RequestParam Long id,
            @RequestParam String emailId
    ){
        UserData userdata = userDataRepository.findById(id).orElseThrow(() -> new RuntimeException("" + id));
        userdata.setEmail(emailId);
        UserData saved = userDataRepository.save(userdata);
        return ResponseEntity.ok(saved);
    }


    @DeleteMapping("/deleteById")
    public String deleteUser(
            @RequestParam Long id
    ){
        userDataRepository.deleteById(id);
        return "User with id = "+ id + " is deleted";
    }


    @PutMapping("/updateEmailByMobile")
    public String updateemailBymobile(
            @RequestParam String mobile,
            @RequestParam String email
    ){
        UserData userdataByEmail = userDataRepository.findByMobile(mobile).orElseThrow(() -> new RuntimeException(mobile));
        userdataByEmail.setEmail(email);
        userDataRepository.save(userdataByEmail);
        return "User Email is Updated by : "+email + " .";
    }

}
