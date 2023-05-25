package pt.com.leogds.infra.error;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

@RestControllerAdvice
public class ErrorTreatment {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Exception> handleError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<String> handleValidations(NoResultException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<String> handleTokenExpired(TokenExpiredException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorTreatment.ErrorsValidation>> handleError400(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(fieldErrors.stream().map(ErrorsValidation::new).toList());
	}
	
	
	private record ErrorsValidation(String field, String message) {
		public ErrorsValidation(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
