package back.pessoas.model.mapper;

import back.pessoas.model.dto.PessoaDto;
import back.pessoas.model.entity.Pessoas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
//
//   // @Mapping(source = "autor.nome", target = "nomeAutor")
//    PessoaDto toDto(Pessoas pessoas);
//
//
//    @InheritInverseConfiguration
//    Pessoas toEntity(Pessoas pessoaDto);
//
//
//    List<PessoaDto> toListDto(List<Pessoas> pessoas);
//
//    @Mapping(target = "id", ignore = true)
//    void updateEntityFromDto(PessoaDto pessoaDto, @MappingTarget Pessoas pessoas);

}
