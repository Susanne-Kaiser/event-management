package org.example.conference.common.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Map<String, List<String>> errors;

	public ValidationException(Map<String, List<String>> errors) {
        super(errors.toString());
        this.errors = Collections.unmodifiableMap(errors);
	}

    public Map<String, List<String>> getErrors() {
        return this.errors;
    }

}
