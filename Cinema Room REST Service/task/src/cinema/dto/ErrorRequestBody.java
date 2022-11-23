package cinema.dto;

public class ErrorRequestBody {
    private String error;

    public ErrorRequestBody(){}

    public ErrorRequestBody(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
