package cinema.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenBody {
    private String token;

    public TokenBody(){}

    @JsonCreator
    public TokenBody(@JsonProperty String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
