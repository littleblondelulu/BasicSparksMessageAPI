package com.ironyard.charlotte;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Spark.init();
          Spark.get(
                "/",
                ((request, response) -> {
                    //Start session and assign session to user's userName
                    Session session = request.session();
                    String name = session.attribute("loginName");
                    User user = users.get(name);

                    //create empty HashMap to use as the model that routes back to the top login page if user == null
                    HashMap m = new HashMap<>();

                    //check to see if user exists & return the right template for the condition
                    if (user == null) {
                        return new ModelAndView(m, "create-user.html");
                    }
                    else {
                        return new ModelAndView(user, "messages.html");
                    }

                }),

                //"traffic director" tool
                new MustacheTemplateEngine();
        );

        Spark.post(
                "/create-user",
                (request, response) -> {
                    //Check if user exists and if not, create new user
                    String name = request.queryParams("loginName");
                    String passwordInput = request.queryParams("passwordInput");
                    User user = users.get(name);
                    if (user == null) {
                        user = new User(name, passwordInput);
                        user.setPassword(passwordInput);
                        users.put(name, user);
                    }


                    //Record user session while logged in and store session to user using loginName "so that subsequent connections can see which user is currently logged in" -Ben Sterret
                    Session session = request.session();
                    session.attribute("loginName", name);


                    //Compare passwords to see if input matches user object's pw
                    if ((passwordInput.length() != user.password.length()) ||
                       (!(user.password.equals(passwordInput)))){
                        //ADD CODE TO PRINT INVALID PW HERE
                        response.redirect("/");
                        return "";
                    } else {
                        response.redirect("/create-message"); return "";
                    }

                    //need to get the username (user) out of session
                    session.invalidate("loginName");
                    response.redirect("/");
                    return "";

                })


        Spark.post(
                "/create-message",
                ((request, response) -> {
                    //Start session and assign session to user's userName
                    String name = request.queryParams("loginName");
                    User user = users.get(name);

                    //Record user session while logged in and store session to user using loginName "so that subsequent connections can see which user is currently logged in" -Ben Sterret
                    Session session = request.session();
                    session.attribute("loginName", name);

                    //Add new user message to user obj's messages ArrayList
                    String addMessage = request.queryParams("add-message");
                    user.messages.add(request.queryParams("add-message"));
                })
        )

         Spark.post(
                "/edit-message",
                    ((request, response) ->{
                        String name = request.queryParams("loginName");
                        User user = users.get(name);
                        Session session=request.session();
                        session.attribute("loginName", name);

                        // Store the message# in String var and change value to an Integer
                        String messageNum = request.queryParams("edit-message#");
                        int i = Integer.parseInt(messageNum);

                        //Replace current message String with new message
                        //Used arrayList index and setter to do this but syntax is wrong(red)
                        //Need to make changes here so that it works
                        String editMessage = user.messages(i-1);
                        editMessage = user.setMessages(request.queryParams("edited-message"));

                        response.redirect("/");
                        return"";
                    })

         );

        //NOTES: personal ref to syntax
        // Message to user obj's messages ArrayList
        //user.get("message#");
        //.put("messageList", userMessages);


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

                       //delete message
                       user.messages.remove(i);


                    response.redirect("/");
                    return "";
                })
         )

        Spark.post(
                "/logout",
                ((request, response) ->{
                Session session=request.session();
                session.invalidate();

                response.redirect("/");
                return"";
            })

         );
      }
}
