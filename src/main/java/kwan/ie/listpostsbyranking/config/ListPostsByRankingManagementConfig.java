package kwan.ie.listpostsbyranking.config;

import kwan.ie.listpostsbyranking.exception.AuthorizationException;
import kwan.ie.listpostsbyranking.exception.ResourceNotFoundException;
import kwan.ie.listpostsbyranking.exception.UserConflictException;
import kwan.ie.listpostsbyranking.presentation.rest.ProblemJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ListPostsByRankingManagementConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor());
    }

    private static ResponseEntity<ProblemJson> handleException(Exception e, HttpStatus status, String type, String title){
        return ResponseEntity
                .status(status)
                .contentType( MediaType.APPLICATION_PROBLEM_JSON)
                .body(new ProblemJson(type, title, e.getMessage(), status.value()
        ));
    }

    /**
     * Globally applicable exception handler
     */
    @ExceptionHandler
    public static ResponseEntity<ProblemJson> handleUnauthorized(AuthorizationException e){
        return unauthorized(e,
                null,
                "Authorization required");
    }

    private static ResponseEntity<ProblemJson> unauthorized(RuntimeException e, String type, String title) {
        return handleException(e, HttpStatus.UNAUTHORIZED, type, title);
    }

    @ExceptionHandler
    public static ResponseEntity<ProblemJson> handleUserConflict(UserConflictException e){
        return conflict(e,
                null,
                "User Conflict");
    }

    private static ResponseEntity<ProblemJson> conflict(RuntimeException e, String type, String title) {
        return handleException(e, HttpStatus.CONFLICT, type, title);
    }

    @ExceptionHandler
    public static ResponseEntity<ProblemJson> handleResourceNotFound(ResourceNotFoundException e){
        return notFound(e,
                null,
                "Resource Not Found");
    }

    private static ResponseEntity<ProblemJson> notFound(RuntimeException e, String type, String title) {
        return handleException(e, HttpStatus.NOT_FOUND, type, title);
    }

}
