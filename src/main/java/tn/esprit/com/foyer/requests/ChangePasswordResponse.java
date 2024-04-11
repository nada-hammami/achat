package tn.esprit.com.foyer.requests;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordResponse {

    @JsonProperty("message")
    private String message;
}
