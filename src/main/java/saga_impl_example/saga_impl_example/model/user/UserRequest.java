package saga_impl_example.saga_impl_example.model.user;

import lombok.Data;

/**
 * User request
 */
@Data
public class UserRequest {
    private String name;
    private String email;
}
