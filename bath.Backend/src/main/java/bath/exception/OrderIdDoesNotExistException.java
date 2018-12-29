package bath.exception;


import bath.response.WrongResponse;

public class OrderIdDoesNotExistException extends Exception {
    private WrongResponse response = new WrongResponse(10003, "Order id does not exists.");

    public WrongResponse getResponse() {
        return response;
    }
}