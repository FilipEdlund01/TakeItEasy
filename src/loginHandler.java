// chain of responsibility pattern
public interface loginHandler {
    void setNext(loginHandler handler);
    void handle(Request request);
}
