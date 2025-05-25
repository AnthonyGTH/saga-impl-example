package saga_impl_example.saga_impl_example.saga.transaction;

import org.springframework.stereotype.Service;

import com.agt.orchestrated.saga.saga.SagaTransaction;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import saga_impl_example.saga_impl_example.model.context.ProcessContext;
import saga_impl_example.saga_impl_example.saga.step.UserOnboardingSagaStep;

/**
 * User onboarding saga transaction
 */
@Service
@RequiredArgsConstructor
public class UserOnboardingSagaTransaction {

    private final UserOnboardingSagaStep steps;

    @SagaTransaction(transaction = "userOnboarding")
    public Mono<ProcessContext> onboardUser(ProcessContext context) {
        return steps.createAccount(context)
                .flatMap(steps::saveProfile)
                .flatMap(steps::sendWelcomeEmail)
                .flatMap(steps::assignPermissions)
                .flatMap(steps::sendWelcomeGift);
    }
}
