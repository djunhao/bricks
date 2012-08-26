package org.northstar.bricks.dao;

import com.google.inject.name.Named;
import com.google.inject.persist.finder.Finder;
import com.google.inject.persist.finder.FirstResult;
import com.google.inject.persist.finder.MaxResults;
import org.northstar.bricks.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface UserFinder {

//	@Finder(query="select u from User u", returnAs=ArrayList.class)
    @Finder(namedQuery = "findAll", returnAs = ArrayList.class)
	List<User> listAll();
	
//	@Finder(query="select u from User u where u.name=:name")
	@Finder(namedQuery = "findByName")
    User getUser(@Named("name") String name);

//    @Finder(query = "select u from User u", returnAs = ArrayList.class)
    @Finder(namedQuery = "findPagedUsers", returnAs = ArrayList.class)
    List<User> listUsers(@FirstResult int first, @MaxResults int max);

    @Finder(namedQuery = "findByNameAndPwd", returnAs = HashSet.class)
    Set<User> authenticated(@Named("name") String name, @Named("password") String password);

}
