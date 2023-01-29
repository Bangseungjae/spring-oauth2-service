package com.example.resourceserver2;

import com.example.sharedobject.Friend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FriendController {

    @GetMapping("/friends")
    public List<Friend> friends(){

        Friend friend1 = getFriend("friend1 ", 10 , " man ");
        Friend friend2 = getFriend("friend2 ", 11 , " woman ");
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(friend1);
        friends.add(friend2);

        return friends;
    }

    private Friend getFriend(String name, int age, String gender) {
        return new Friend(name, age, gender);
    }
}
