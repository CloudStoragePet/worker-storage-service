package org.brain.workerstorageservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.brain.workerstorageservice.exceptions.model.ErrorType;
import java.io.Serial;


@EqualsAndHashCode(callSuper = true)
@Data
public class TaskCanceledException extends ServiceException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final ErrorType errorType = ErrorType.PROCESSING_ERROR;
    private static final String DEFAULT_MESSAGE = "Task canceled exception!";

    public TaskCanceledException() {
        this(DEFAULT_MESSAGE);
    }

    public TaskCanceledException(String message) {
        super(message);
    }
}
