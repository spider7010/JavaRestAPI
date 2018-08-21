package com.santhosh.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private List<User> users = new ArrayList<>();

    private int userCount = 3;

    @PostConstruct
    public void init() {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3 , "Jack", new Date()));
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(Integer id){
        return users.stream().filter(i -> i.getId()==id).findAny().get();
    }

    public User deleteById(int id){
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(id==user.getId()){
                it.remove();
                return user;
            }
        }
        return null;
    }
}
