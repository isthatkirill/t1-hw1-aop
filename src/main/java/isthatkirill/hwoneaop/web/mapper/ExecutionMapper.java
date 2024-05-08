package isthatkirill.hwoneaop.web.mapper;

import isthatkirill.hwoneaop.model.Execution;
import isthatkirill.hwoneaop.web.dto.ExecutionDto;
import org.mapstruct.Mapper;

/**
 * @author Kirill Emelyanov
 */

@Mapper(componentModel = "spring")
public interface ExecutionMapper extends Mappable<Execution, ExecutionDto> {
}
