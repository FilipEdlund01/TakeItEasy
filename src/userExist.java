public class userExist implements loginHandler{
    private loginHandler next;

    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {
        API.getInstance().findUserInDatabase(request.getUserName()); // make the sql request to database
        if(API.getInstance().isUserFound()){
            System.out.println("user found");
        }
    }
      /*  if (request.getValue() < 10) {
            System.out.println("Request handled by LessThanTenHandler");
        } else if (next != null) {
            next.handle(request);
        }
    }*/
}
