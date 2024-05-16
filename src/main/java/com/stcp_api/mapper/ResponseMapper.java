package com.stcp_api.mapper;

import com.stcp_api.domain.model.BusStopDTO;
import com.stcp_api.domain.model.stcpresponse.SearchBusStopModel.StopRecord;
import java.awt.geom.Point2D;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseMapper {

  ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

  @Mapping(target = "code", source = "nome",qualifiedByName = "extractStopCodeFromRecord")
  @Mapping(target = "name", source = "nome", qualifiedByName = "extractNameFromRecord")
  @Mapping(target = "zone", source = "morada")
  @Mapping(target = "coordinates", source = "geom", qualifiedByName = "extractCoords")
  BusStopDTO responseStopRecordToBusStop(StopRecord stopRecord);

  @Named("extractStopCodeFromRecord")
  default String extractStopCodeFromRecord(String nome) {
    Pattern pattern = Pattern.compile("\\[(.*?)]");
    Matcher matcher = pattern.matcher(nome);
    return matcher.find() ? matcher.group(1) : null;
  }

  @Named("extractNameFromRecord")
  default String extractNameFromRecord(String nome) {
    int commaIndex = nome.indexOf(",");
    return nome.substring(commaIndex + 2);
  }

  @Named("extractCoords")
  default Point2D.Double extractCoordinatesFromRecord(String geom) {
    Pattern pattern = Pattern.compile("\\(([^\\s]+)\\s([^\\s]+)\\)");
    Matcher matcher = pattern.matcher(geom);

    return matcher.find()
        ? new Point2D.Double(Double.parseDouble(matcher.group(1)), Double.parseDouble(matcher.group(2)))
        : null;
    }
}
