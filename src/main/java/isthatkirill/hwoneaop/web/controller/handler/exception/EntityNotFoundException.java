package isthatkirill.hwoneaop.web.controller.handler.exception;

/**
 * @author Kirill Emelyanov
 */


public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<?> aClass, Long id) {
        super(aClass.getSimpleName() + " with id = " + id + " not found.");
    }
}
