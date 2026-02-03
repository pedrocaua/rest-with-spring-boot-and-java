package com.pedrocaua.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}

// Usado para formatar essa exceção retornando um JSON