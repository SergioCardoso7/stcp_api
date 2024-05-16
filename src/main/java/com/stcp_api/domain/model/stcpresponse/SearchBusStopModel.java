package com.stcp_api.domain.model.stcpresponse;

import java.util.List;

public record SearchBusStopModel(
    String sort,
    int recordsReturned,
    int totalRecords,
    List<StopRecord> records,
    int startIndex,
    String dir
) {

  public record StopRecord(
      String morada,
      String tipox,
      String geom,
      String tipo,
      String nome
  ){}

}
