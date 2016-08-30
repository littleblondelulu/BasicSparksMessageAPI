package com.ironyard.charlotte;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();


    public static void main(String[] args) {
        User lulu = new User("lulu", "2345");
        users.put("lulu", lulu);

        Spark.init();

          Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();

                    String name = session.attribute("userName");
                    User user = users.get(name);

                    HashMap x = new HashMap<>();

                    //return the correct template
                    if (user == null) {
                        return new ModelAndView(x, "login.html");
                    }
                    else {
                        x.put("name", user.name);
                        x.put("messageList", user.messages);
                        return new ModelAndView(x, "home.html");
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
                    String password = request.queryParams("password");
                    if (user == null) {
                        user = new User(name, password);
                        users.put(name, user);
                    }

                    //Record user session while logged in and store session to user using loginName "so that subsequent connections can see which user is currently logged in" -Ben Sterret
                    //Compare passwords to see if match
                   if (!users.get(name).password.equals(password) && user != null) {
                        Session session = request.session();
                        session.invalidate();

                       response.redirect("/");
                    }

                    Session session = request.session();
                    session.attribute("userName", name);


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
                    String name = request.queryParams("userName");
                    User user = users.get(name);

                    Session session = request.session();
                    session.attribute("userName");

                    //Add new user message to user obj's messages ArrayList
                    String message = request.queryParams("addMessage");
                    Message m = new Message(message);

                    user.messages.add(m);

                    response.redirect("/");
                    return "";
                })
        );

         Spark.post(
                "/edit-message",
                    ((request, response) ->{
                        String name = request.queryParams("userName");
                        User user = users.get(name);

                        Session session=request.session();
                        session.attribute("userName");

                        String editMessage = request.queryParams("editMessage#");
                        int i = Integer.parseInt(editMessage);

                        //store the String from (message#) index in m

                        //store the String  want to replace with the current message
                        String message = request.queryParams("editedMessage");
                        Message z = new Message(message);
//                        m.setMessage(message);

                        user.messages.set(i-1, z);

                        response.redirect("/");
                        return"";
                    })

         );

         Spark.post(
                "/delete-message",
                   ((request, response) ->{
                        String name = request.queryParams("userName");
                        User user = users.get(name);
                        Session session=request.session();
                        session.attribute("userName");

                       //change message# to int to get the index
                       String messageNum = request.queryParams("deleteMessage#");
                       int i = Integer.parseInt(messageNum);
                       user.messages.remove(i-1);

                    response.redirect("/");
                    return "";
                })
         );

      }
}
