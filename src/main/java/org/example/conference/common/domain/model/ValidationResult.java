package org.example.conference.common.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationResult {

    private Map<String, List<String>> errors;

    public ValidationResult() {
        this.errors = new HashMap<>();
    }

    public void addError(String fieldName, String errorMsg) {
        List<String> errorList = this.errors.get(fieldName);
        if (errorList == null) {
            errorList = new ArrayList<>();
            this.errors.put(fieldName, errorList);
        }
        errorList.add(errorMsg);
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }

    public Map<String, List<String>> getErrors() {
        return Collections.unmodifiableMap(this.errors);
    }

    public void validate() {
        if (hasErrors()) {
            throw new ValidationException(getErrors());
        }
    }

}
