package com.stcp_api.domain.utils;

import lombok.Getter;

@Getter
public class StcpSearchEndpoint extends StcpEndpoint{

  private String queryString;
  private boolean fltBus;
  private boolean fltMetro;
  private boolean fltCp;
  private boolean fltTop;
  private boolean fltPoi;

  private StcpSearchEndpoint(StcpSearchEndpointBuilder builder) {
    this.basePath = builder.basePath;
    this.action = builder.action;
    this.queryString = builder.queryString;
    this.fltBus = builder.fltBus;
    this.fltMetro = builder.fltMetro;
    this.fltCp = builder.fltTcp;
    this.fltTop = builder.fltTop;
    this.fltPoi = builder.fltPoi;
  }

  @Override
  public String toString() {
    return new StringBuilder(basePath)
        .append("?action=").append(action)
        .append("&qrystr=").append(queryString)
        .append("&fltbus=").append(fltBus)
        .append("&fltmetro=").append(fltMetro)
        .append("&fltcp=").append(fltCp)
        .append("&flttop=").append(fltTop)
        .append("&fltpoi=").append(fltPoi).toString();
  }


  public static class StcpSearchEndpointBuilder {

    private String basePath;
    private String action;
    private String queryString;
    private boolean fltBus;
    private boolean fltMetro;
    private boolean fltTcp;
    private boolean fltTop;
    private boolean fltPoi;

    public StcpSearchEndpointBuilder(String basePath, String action) {
      this.basePath = basePath;
      this.action = action;
    }

    public StcpSearchEndpointBuilder withQueryString(String queryString) {
      this.queryString = queryString;
      return this;
    }

    public StcpSearchEndpointBuilder withFltBus(boolean fltBus) {
      this.fltBus = fltBus;
      return this;
    }

    public StcpSearchEndpointBuilder withFltMetro(boolean fltMetro) {
      this.fltMetro = fltMetro;
      return this;
    }

    public StcpSearchEndpointBuilder withFltTcp(boolean fltTcp) {
      this.fltTcp = fltTcp;
      return this;
    }

    public StcpSearchEndpointBuilder withFltTop(boolean fltTop) {
      this.fltTop = fltTop;
      return this;
    }

    public StcpSearchEndpointBuilder withFltPoi(boolean fltPoi) {
      this.fltPoi = fltPoi;
      return this;
    }

    public StcpSearchEndpoint build() {
      return new StcpSearchEndpoint(this);
    }

  }

}
