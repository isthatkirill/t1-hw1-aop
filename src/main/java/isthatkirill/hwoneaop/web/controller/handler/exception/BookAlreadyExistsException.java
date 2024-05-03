package isthatkirill.hwoneaop.web.controller.handler.exception;

/**
 * @author Kirill Emelyanov
 */

public class BookAlreadyExistsException extends RuntimeException {

    public BookAlreadyExistsException(String isbn) {
        super("Book with isbn = " + isbn + " already exists.");
    }

}
