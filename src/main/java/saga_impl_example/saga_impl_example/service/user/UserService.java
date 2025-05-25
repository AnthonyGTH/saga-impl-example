package saga_impl_example.saga_impl_example.service.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import saga_impl_example.saga_impl_example.model.context.ProcessContext;
import saga_impl_example.saga_impl_example.model.user.UserRequest;
import saga_impl_example.saga_impl_example.saga.transaction.UserOnboardingSagaTransaction;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserOnboardingSagaTransaction saga;

    public Mono<ProcessContext> onboardUser(UserRequest request) {
        ProcessContext ctx = new ProcessContext();
        ctx.name = request.getName();
        ctx.email = request.getEmail();
        return saga.onboardUser(ctx);
    }
}
