package com.ironyard.charlotte;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    //Test
    User user = new User("lulu", "2345");


    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();
          Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String name = session.attribute("userName");
                    User user = users.get(name);

                    //return the correct template
                    HashMap m = new HashMap();
                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    }
                    else {
                        return new ModelAndView(user, "home.html");
                    }

                }),

                //"traffic director"
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    //Check if user exists and if not, create new user
                    String name = request.queryParams("userName");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User("userName", name);
                        users.put(name, user);
                    }

                    //Record user session while logged in and store session to user using loginName "so that subsequent connections can see which user is currently logged in" -Ben Sterret
                    Session session = request.session();
                    session.attribute("userName", name);

                    String passwordInput = request.queryParams("password");
                    String password = user.getPassword();

                    HashMap m = new HashMap();

                    //Compare passwords to see if match
                  //  if ((!(passwordInput.equals(password)))) {
                    //    return new ModelAndView(m, "login.html");
                    //}

                    response.redirect("/");
                    return "";

                })
        );

        Spark.post(
                "/invalidPassword",
                ((request, response) ->{
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/logout",
                ((request, response) ->{
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-message",
                ((request, response) -> {
                    //Start session and assign it to user
                    String name = request.queryParams("loginName");
                    User user = users.get(name);

                    Session session = request.session();
                    session.attribute("loginName", name);

                    //Add new user message to user obj's messages ArrayList
                    String message = request.queryParams("addMessage");
                    Message m = new Message();

                    m.message = message;
                    User.messages.add(m);

                    response.redirect("/");
                    return "";
                })
        );

         Spark.post(
                "/edit-message",
                    ((request, response) ->{
                        String name = request.queryParams("loginName");
                        User user = users.get(name);

                        Session session=request.session();
                        session.attribute("loginName", name);

                        String editMessage = request.queryParams("editMessage#");
                        int i = Integer.parseInt(editMessage);

                        //store the String from (message#) index in m
                        Message m = User.messages.get(i);

                        //store the String  want to replace with the current message
                        String message = request.queryParams("editedMessage");

                        m.setMessage(message);

                        User.messages.add(m);

                        response.redirect("/");
                        return"";
                    })

         );

         Spark.post(
                "/delete-message",
                   ((request, response) ->{
                        String name = request.queryParams("loginName");
                        User user = users.get(name);
                        Session session=request.session();
                        session.attribute("loginName", name);

                       //change message# to int to get the index
                       String messageNum = request.queryParams("delete-message#");
                       int i = Integer.parseInt(messageNum);
                       user.messages.remove(i);

                    response.redirect("/");
                    return "";
                })
         );

      }
}
