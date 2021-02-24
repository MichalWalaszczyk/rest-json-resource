package pl.walaszczyk.restjsonresource.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GetDataFromJsonException extends RuntimeException {

    public GetDataFromJsonException(String message) {
        super(message);
    }
}
