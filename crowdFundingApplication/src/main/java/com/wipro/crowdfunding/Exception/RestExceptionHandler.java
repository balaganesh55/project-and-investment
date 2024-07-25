package com.wipro.crowdfunding.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	// Other exception handlers...
	@ExceptionHandler(InvalidDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleInvalidDataException(InvalidDataException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//		Map<String, List<String>> body = new HashMap<>();
//		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
//		body.put("errors", errors);
//		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(handleInvalidDataException(new InvalidDataException("Invalid Data")),
				HttpStatus.BAD_REQUEST);

	}
}