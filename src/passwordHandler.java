public class passwordHandler implements loginHandler {
    private final static passwordHandler passwordHandler = new passwordHandler();
    private loginHandler next;

    public boolean b;


    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {

        if(API.getInstance().CheckPassword(StartStage.getInstance().getUserName(), request.getInputText())){

            StartStage.getInstance().setMessage("");

            b=true;

        }else{
            b=false;
            System.out.println("incorect password");
            StartStage.getInstance().setMessage("incorrect password");
        }



     }

     public static passwordHandler getInstance(){
        return  passwordHandler;
     }


}
