package io.swagger.model.merchandise_provider;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseEntityMerchandise   {
  
  @Schema(description = "")
  private Map headers = null;
  
  @Schema(description = "")
  private Merchandise body = null;
  
  @Schema(description = "")
  private HttpStatusCode statusCode = null;
  
  @Schema(description = "")
  private Integer statusCodeValue = null;
 /**
   * Get headers
   * @return headers
  **/
  @JsonProperty("headers")
  public Map getHeaders() {
    return headers;
  }

  public void setHeaders(Map headers) {
    this.headers = headers;
  }

  public ResponseEntityMerchandise headers(Map headers) {
    this.headers = headers;
    return this;
  }

 /**
   * Get body
   * @return body
  **/
  @JsonProperty("body")
  public Merchandise getBody() {
    return body;
  }

  public void setBody(Merchandise body) {
    this.body = body;
  }

  public ResponseEntityMerchandise body(Merchandise body) {
    this.body = body;
    return this;
  }

 /**
   * Get statusCode
   * @return statusCode
  **/
  @JsonProperty("statusCode")
  public HttpStatusCode getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(HttpStatusCode statusCode) {
    this.statusCode = statusCode;
  }

  public ResponseEntityMerchandise statusCode(HttpStatusCode statusCode) {
    this.statusCode = statusCode;
    return this;
  }

 /**
   * Get statusCodeValue
   * @return statusCodeValue
  **/
  @JsonProperty("statusCodeValue")
  public Integer getStatusCodeValue() {
    return statusCodeValue;
  }

  public void setStatusCodeValue(Integer statusCodeValue) {
    this.statusCodeValue = statusCodeValue;
  }

  public ResponseEntityMerchandise statusCodeValue(Integer statusCodeValue) {
    this.statusCodeValue = statusCodeValue;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseEntityMerchandise {\n");
    
    sb.append("    headers: ").append(toIndentedString(headers)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    statusCodeValue: ").append(toIndentedString(statusCodeValue)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
