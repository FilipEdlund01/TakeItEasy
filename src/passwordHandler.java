public class passwordHandler implements loginHandler {
    private final static passwordHandler passwordHandler = new passwordHandler();
    private loginHandler next;


    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {
        StartStage.getInstance().setMessage("User found, type password");
        // System.out.println("User does not exist");
        // StartStage.getInstance().setLoginFailed(true);


     }

     public static passwordHandler getInstance(){
        return  passwordHandler;
     }


}
