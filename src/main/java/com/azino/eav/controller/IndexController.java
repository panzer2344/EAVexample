package com.azino.eav.controller;

import com.azino.eav.model.User;
import com.azino.eav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    //@Autowired
    /*private EntityService entityService;*/
    //private AttributeService attributeService;

    @Autowired
    private UserService userService;



    /* <---------------------------------------------------> */
    /* <---------------------------------------------------> */
    /* <---------------------------------------------------> */
    /*                                                       */
    /* name of operations written and all methods are get    */
    /* because i will use only browser queries with arguments*/
    /* in url                                                */
    /* <---------------------------------------------------> */
    /* <---------------------------------------------------> */
    /* <---------------------------------------------------> */

    @GetMapping("add/{username}/{firstName}/{lastName}/{age}")
    public String getCreatedEntity(
            @PathVariable("username") String username,
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("age") Short age
    ) {

        /* ==== */
        /* test */
        /* ==== */

        User user = userService.save(
                new User(
                        null,
                        username,
                        firstName,
                        lastName,
                        age
                )
        );

        /* ======== */
        /* end test */
        /* ======== */

        return user.toString();
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "Successfully deleted!";
    }

    @GetMapping("delete/entity/{id}")
    public String deleteByEntityId(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        userService.delete(user);
        return "Successfully deleted! entity: " + user;
    }

    @GetMapping("find/{id}")
    public String findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return user.toString();
    }

    @GetMapping("find/all")
    public String findAll() {
        List<User> users = userService.findAll();
        return users.toString();
    }

    @GetMapping("update/{id}/{username}/{firstName}/{lastName}/{age}")
    public String method(
            @PathVariable("id") Long id,
            @PathVariable("username") String username,
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @PathVariable("age") Short age
    ) {
        User user = userService.findById(id);

        User newUser = new User(
            id,
            username,
            firstName,
            lastName,
            age
        );

        if(null != user) {
            newUser = userService.update(id, newUser);
            if(null != newUser) {
                return "Updated, entity: " + newUser;
            } else {
                return "Cant update!";
            }
        }
        return "User is null";
    }


}
