public class handleNewUser implements loginHandler{
    private final static handleNewUser handleNewUser = new handleNewUser();
    public boolean b;



    private loginHandler next;

    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {

        if(!API.getInstance().CheckIfUSerExistInDatabase(request.getInputText())){// if it return true we dont want to proceed because its bad to have to players with the same username
            StartStage.getInstance().setUserName(request.getInputText());
            StartStage.getInstance().setMessage("Valid username please type password");
            StartStage.getInstance().setUserName(request.getInputText());
            b = true;



        }else{
            b =false;

            StartStage.getInstance().setMessage("User with same name already exist ): try something else");
        }
    }



    public static handleNewUser getInstance(){
        return handleNewUser;
    }

}
