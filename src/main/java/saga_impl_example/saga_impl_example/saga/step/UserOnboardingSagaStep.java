package saga_impl_example.saga_impl_example.saga.step;

import org.springframework.stereotype.Service;

import com.agt.orchestrated.saga.saga.SagaCompensation;

import reactor.core.publisher.Mono;
import saga_impl_example.saga_impl_example.model.context.ProcessContext;

/**
 * User onboarding saga step
 */
@Service
public class UserOnboardingSagaStep {

    @SagaCompensation(rollbackFunction = "rollbackCreateAccount")
    public Mono<ProcessContext> createAccount(ProcessContext ctx) {
        System.out.println("[STEP] Creating user account...");
        ctx.accountCreated = true;
        ctx.userId = "user-" + System.currentTimeMillis();
        return Mono.just(ctx);
    }

    public Mono<Void> rollbackCreateAccount(ProcessContext ctx) {
        System.out.println("[ROLLBACK] Deleting user account...");
        ctx.accountCreated = false;
        return Mono.empty();
    }

    @SagaCompensation(rollbackFunction = "rollbackSaveProfile")
    public Mono<ProcessContext> saveProfile(ProcessContext ctx) {
        System.out.println("[STEP] Saving user profile...");
        ctx.profileSaved = true;
        return Mono.just(ctx);
    }

    public Mono<Void> rollbackSaveProfile(ProcessContext ctx) {
        System.out.println("[ROLLBACK] Reverting profile save...");
        ctx.profileSaved = false;
        return Mono.empty();
    }

    @SagaCompensation(rollbackFunction = "rollbackSendEmail")
    public Mono<ProcessContext> sendWelcomeEmail(ProcessContext ctx) {
        System.out.println("[STEP] Sending welcome email to: " + ctx.email);
        ctx.emailSent = true;
        return Mono.just(ctx);
    }

    public Mono<Void> rollbackSendEmail(ProcessContext ctx) {
        System.out.println("[ROLLBACK] Cancelling welcome email...");
        ctx.emailSent = false;
        return Mono.empty();
    }

    @SagaCompensation(rollbackFunction = "rollbackAssignPermissions")
    public Mono<ProcessContext> assignPermissions(ProcessContext ctx) {
        System.out.println("[STEP] Assigning permissions...");
        ctx.permissionAssigned = true;
        return Mono.just(ctx);
    }

    public Mono<Void> rollbackAssignPermissions(ProcessContext ctx) {
        System.out.println("[ROLLBACK] Removing permissions...");
        ctx.permissionAssigned = false;
        return Mono.empty();
    }

    @SagaCompensation(rollbackFunction = "rollbackSendGift")
    public Mono<ProcessContext> sendWelcomeGift(ProcessContext ctx) {
        System.out.println("[STEP] Sending welcome gift...");
        return Mono.error(new RuntimeException("Simulated failure sending gift"));
    }

    public Mono<Void> rollbackSendGift(ProcessContext ctx) {
        System.out.println("[ROLLBACK] Retracting welcome gift...");
        ctx.welcomeGiftSent = false;
        return Mono.empty();
    }
}