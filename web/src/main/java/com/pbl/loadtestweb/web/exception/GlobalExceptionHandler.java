package com.pbl.loadtestweb.web.exception;

import com.pbl.loadtestweb.common.common.CommonFunction;
import com.pbl.loadtestweb.common.exception.*;
import com.pbl.loadtestweb.common.payload.general.ResponseDataAPI;
import com.pbl.loadtestweb.common.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ResponseDataAPI> notFoundException(NotFoundException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ResponseDataAPI> badRequestException(BadRequestException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ForBiddenException.class)
  public ResponseEntity<ResponseDataAPI> forbiddenException(ForBiddenException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<ResponseDataAPI> internalServerException(InternalServerException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ResponseDataAPI> unauthorizedException(UnauthorizedException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(BadGatewayException.class)
  public ResponseEntity<ResponseDataAPI> badGatewayException(BadGatewayException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.BAD_GATEWAY);
  }

  @ExceptionHandler(GatewayTimeoutException.class)
  public ResponseEntity<ResponseDataAPI> gatewayTimeoutException(GatewayTimeoutException ex) {
    ErrorResponse errorResponse = CommonFunction.getExceptionError(ex.getMessage());
    ResponseDataAPI responseDataAPI = ResponseDataAPI.error(errorResponse);
    return new ResponseEntity<>(responseDataAPI, HttpStatus.GATEWAY_TIMEOUT);
  }
}
