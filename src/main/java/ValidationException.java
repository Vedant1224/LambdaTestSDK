public class ValidationException extends Exception {
    public ValidationException(String message){
        super(message);
    }
}

class HTTPException extends Exception {
    public HTTPException(String message){
        super(message);
    }
}
