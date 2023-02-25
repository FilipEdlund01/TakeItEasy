

public class ChainDemo   {
    public static void main(String[] args) {

        loginHandler  userExist = new userExist();
        loginHandler userDontExist = new passwordHandler();

        userExist.setNext(userDontExist);
        userExist.handle(new Request("fip"));
        //API.getInstance().findUserInDatabase("John");



       /* lessThanTenHandler.setNext(greaterThanTenHandler);

        lessThanTenHandler.handle(new Request(5));
        lessThanTenHandler.handle(new Request(15));*/


    }
}