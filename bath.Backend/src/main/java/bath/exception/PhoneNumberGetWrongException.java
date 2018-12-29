package bath.exception;

import bath.response.WrongResponse;

public class PhoneNumberGetWrongException extends Exception {
    private WrongResponse response = new WrongResponse(10008, "Cannot decrypt the data.");

    public WrongResponse getResponse() {
        return response;
    }
}