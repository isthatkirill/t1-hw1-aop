package isthatkirill.hwoneaop.web.mapper;

/**
 * @author Kirill Emelyanov
 */

public interface Mappable<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
