package kwan.ie.listpostsbyranking.presentation;

import org.jboss.jandex.AnnotationTarget;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
/**
 * Annotation used to signal that a given controller action requires authentication
 */
public @interface AuthorizationRequired{}
