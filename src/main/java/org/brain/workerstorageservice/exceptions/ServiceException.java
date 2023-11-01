package org.brain.workerstorageservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.brain.workerstorageservice.exceptions.model.ErrorType;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    private ErrorType errorType = ErrorType.PROCESSING_ERROR;

    ServiceException() {
        super();
    }
    ServiceException(String message) {
        super(message);
    }

}
