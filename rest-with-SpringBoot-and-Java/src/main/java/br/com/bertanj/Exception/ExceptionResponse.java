package br.com.bertanj.Exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
