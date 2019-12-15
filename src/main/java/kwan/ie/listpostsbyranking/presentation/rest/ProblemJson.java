package kwan.ie.listpostsbyranking.presentation.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Class used for error models, based on the <a href="https://tools.ietf.org/html/rfc7807">Problem Json spec</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemJson {
    private final String type;
    private final String title;
    private final String detail;
    private final int status;

    public ProblemJson(String type, String title, String detail, int status) {
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getStatus() {
        return status;
    }
}
