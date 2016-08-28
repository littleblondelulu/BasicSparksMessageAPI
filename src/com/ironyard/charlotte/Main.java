package com.ironyard.charlotte;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {

//Lulu note: session attribute takes (String val, object value)

        Spark.init();
        Spark.get(
                "/",
                ((request, response) -> {

                    //Create & Request a Session to track user activity
                    Session session = request.session();
                    String name = session.attribute("loginName");
                    User user = users.get(name);

                    //check to see if user exists

                    //created an empty HashMap (m) to use it as an empty "stand-in" HashMap
                    //m = empty HashMap to use as the model argument for the null user endpoint
                    HashMap m = new HashMap<>();

                    //if user is null - route back to /create-user which
                    //Since page doesn't have or display any of the user's saved data the page interface doesn't need to model any user "stuff" so can use m as the empty model here
                    if (user == null) {
                        return new ModelAndView(m, "create-user.html");
                    }
                    else {
                        //route user to the messages template and model the template'd data for the current user
                        return new ModelAndView(user, "messages.html");
                    }


                }),

                //"traffic director"
                new MustacheTemplateEngine();

        );


        Spark.post(
                "/create-user",
                ((request, response) -> {
                    "/create-user",
                            ((request, response) -> {
                                String name = request.queryParams("loginName");
                                User user = users.get(name);
                                if (user == null) {
                                    user = new User(name);
                                    users.put(name, user);
                                }

                                Session session = request.session();
                                session.attribute("userName", name);

                            }

                    //Request session / Assign to current user / Start session as soon as user logs in
                    Session session = request.session();

                    //Store loginName and password from user into variables which will use to decipher if matches a user obj in users Hash
                    String name = request.queryParams("loginName");
                    //Assigns the current user to the user that's logged in
                    User user = users.get(name);

                    //declare two password variables - one to store pw entered by user and the other to access user's stored password in user's object
                   //can use passwordEntered to do two things: 1. set new User passwords and 2. test/Match user pw
                    String passwordEntered = request.queryParams("passwordEntered");
                    String userPassword = user.getPassword();


                    //Check if user exists -- if not; handle by creating new user object
                    if (user == null) {
                        //Create new user object & save user to users HashMap
                        user = new User(name, passwordEntered);
                        user = users.put(name, user);
                    }

                    //Compare password from user to the object's password -
        //WANT: to print String from java to a textfield in the "login" html --- HOW??

                    // if passwords don't match - deny access and handle the situation in if stmt
                    if (!(passwordEntered.equals(userPassword)) {
                        response.redirect("/create-message");
                        return "";
                    } else {
                        response.redirect("/create-message");
                        return "";
                        //print "Invalid password" in the api text
                        //System.out.print("Invalid password");

  //and want to handle here the situation by printing a response on the API /login
// System  = html textbox ------ want to connect Java string to the page -- here's my fluff code...
                    }

                   response.redirect("/");
                    return "";

                    })

                };


        Spark.post(
                "/create-message",
                ((request, response) -> {

      //Start user session and assign current user to the lambda's user variable
                    Session session = request.session();
                    String name = session.attribute("loginName");
                    user = users.get(name);

                    //Add new user message to user obj's messages ArrayList
                    user.messages.add(request.queryParams("AddNewMessage"));
]

         //Original code: I first wrote the code below to accomplish the add message process and kept it commented out below
        //In line 111 - combined 2 steps into one line of code - less space, clean, and less room for error
                        //String addNewMessage = request.queryParams("AddNewMessage");
                        //String messageClass = user.Message.add(addNewMessage);


                    user.get("message#", user);
                    //.put("messageList", userMessages);

//"DO PERFORM AN ACTION ON EXISTING MESSAGE
// ---------- Edit or Delete

//sort message arrayList *next*
// find index of message from arrayList and store message in
     //need to use tDtring to convert the num
    //TYPES - int mesaage#(msg by index in array --- CONSIDER BT STRINGS AN INDEX

                    // Store the index# of the message that the user wants to change
                    int messageNum = request.queryParams("Message#");
                    if(mess)
                    String deleteMessage

                    //Need to add  (+1 ?? zero-bas)  --
                    String messageUnderConstruction;
                    int messageUnderConstruction = users.get(user.getUserMessages(messageNum));
                    user.userMessages.toString;



                    String userAction = request.queryParams("alter-message");
//change the syntx ofthis if stmt
                    if(userAction selected EQUALS edit-message")
                    "delete-message"
                    else(userAction selected EQUALS edit-message"){
                    //1. get message# (use - messageNum)



                    users.get(user.getUserMessages());

                    boolean alterUserMessage = ()


                    // If(EDIT) --


                    response.redirect("/");
                    return "";


})
                }

 Spark.post(
         "/logout",
         ((request, response) -> {
         Session session = request.session();
         session.invalidate();
         response.redirect("/");
         return "";

      }
}
