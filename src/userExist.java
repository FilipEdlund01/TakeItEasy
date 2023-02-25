public class userExist implements loginHandler{
    // it is better to make the userExist singleton because
    // the class is connecting to database
    // the singleton pattern avoid repetitive connections to database every time we make instance of the class
    private final static userExist userExist = new userExist();
    public boolean b;



    private loginHandler next;

    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {
        API.getInstance().findUserInDatabase(request.getInputText()); // make the sql request to database
        if(API.getInstance().isUserFound()){
            StartStage.getInstance().setUserName(request.getInputText());
            StartStage.getInstance().setMessage("User found, type password");

            b=true;

        }else{
            b=false;
            StartStage.getInstance().setMessage("User not found");
        }
    }



    public static userExist getInstance(){
        return userExist;
    }


}
