public class passwordHandler implements loginHandler {
    private final static passwordHandler passwordHandler = new passwordHandler();
    private loginHandler next;

    public boolean b;


    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {
        API.getInstance().CheckPassword(StartStage.getInstance().getUserName(), request.getInputText()); // make the sql request to database
        if(API.getInstance().isPasswordFound()){

            StartStage.getInstance().setMessage("correct password");

            b=true;

        }else{
            b=false;
            StartStage.getInstance().setMessage("password");
        }
        // System.out.println("User does not exist");
        // StartStage.getInstance().setLoginFailed(true);


     }

     public static passwordHandler getInstance(){
        return  passwordHandler;
     }


}
