public class userDontExist implements loginHandler {
    private loginHandler next;

    public void setNext(loginHandler handler) {
        this.next = handler;
    }

    public void handle(Request request) {
        System.out.println("User does not exist");

     }
}
