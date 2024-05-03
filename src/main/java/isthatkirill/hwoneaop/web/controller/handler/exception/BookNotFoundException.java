package isthatkirill.hwoneaop.web.controller.handler.exception;

/**
 * @author Kirill Emelyanov
 */


public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book with id = " + id + " not found.");
    }
}
