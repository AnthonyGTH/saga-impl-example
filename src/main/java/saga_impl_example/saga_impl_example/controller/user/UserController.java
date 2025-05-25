package saga_impl_example.saga_impl_example.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import saga_impl_example.saga_impl_example.model.context.ProcessContext;
import saga_impl_example.saga_impl_example.model.user.UserRequest;
import saga_impl_example.saga_impl_example.service.user.UserService;

// Controller
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/onboard")
    public Mono<ProcessContext> onboardUser(@RequestBody UserRequest request) {
        return userService.onboardUser(request);
    }
}
