package efrei.propertyStake.controllers;

import efrei.propertyStake.models.PaymentTransaction;
import efrei.propertyStake.services.PaymentTransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentTransactionController {

    private final PaymentTransactionService paymentTransactionService;

    public PaymentTransactionController(PaymentTransactionService paymentTransactionService) {
        this.paymentTransactionService = paymentTransactionService;
    }

    @GetMapping
    public List<PaymentTransaction> getAllTransactions() {
        return paymentTransactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public PaymentTransaction getById(@PathVariable UUID id) {
        return paymentTransactionService.getTransactionById(id);
    }

    /**
     * Exemple d'appel:
     * POST /payments
     * {
     *   "investorId": "...",
     *   "amount": 1500.0,
     *   "status": "SUCCESS"
     * }
     */
    @PostMapping
    public PaymentTransaction createTransaction(@RequestBody PaymentRequest request) {
        return paymentTransactionService.createTransaction(
                request.getInvestorId(),
                request.getAmount(),
                request.getStatus()
        );
    }

    @DeleteMapping("/{id}")
    public boolean deleteTransaction(@PathVariable UUID id) {
        return paymentTransactionService.deleteTransaction(id);
    }

    // DTO
    public static class PaymentRequest {
        private UUID investorId;
        private double amount;
        private String status;

        public UUID getInvestorId() {
            return investorId;
        }
        public void setInvestorId(UUID investorId) {
            this.investorId = investorId;
        }
        public double getAmount() {
            return amount;
        }
        public void setAmount(double amount) {
            this.amount = amount;
        }
        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
