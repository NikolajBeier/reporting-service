package reporting.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import messaging.Event;
import messaging.MessageQueue;
import reporting.service.DTO.CustomerReportDTO;
import reporting.service.DTO.ManagerReport;
import reporting.service.DTO.MerchantReportDTO;

import java.util.stream.Collectors;

public class ReportingService {

    //TODO: consider changing synchronized of methods, it is not scalable or Concurrent, maybe request id method or something else

    private MessageQueue queue;
    private CompletableFuture<List<Payment>> paymentsFuture = new CompletableFuture<>();

    public ReportingService(MessageQueue queue) {
        this.queue = queue;
        queue.addHandler("PaymentsList", this::handlePaymentsList);
    }

    public synchronized CompletableFuture<ManagerReport> createManagerReport() {
        requestPaymentsList();

        //manager gets all information from payment!

        return paymentsFuture.thenApply(paymentList -> {
            ManagerReport managerReport = new ManagerReport();
            managerReport.setPaymentList(new ArrayList<>(paymentList));
            return managerReport;
        });
    }

    public synchronized CompletableFuture<List<CustomerReportDTO>> createCustomerReport(String customerId) {
        requestPaymentsList();

        //filter information from payment to match DTO requirement!

        return paymentsFuture.thenApply(paymentList ->
                paymentList.stream()
                        .filter(payment -> customerId.equals(payment.getCustomerId()))
                        .map(payment -> new CustomerReportDTO(payment.getToken(), payment.getAmount(), payment.getMerchantId()))
                        .collect(Collectors.toList())
        );
    }

    public synchronized CompletableFuture<List<MerchantReportDTO>> createMerchantReport(String merchantId) {
        requestPaymentsList();

        //filter information from payment to match DTO requirement!

        return paymentsFuture.thenApply(paymentList ->
                paymentList.stream()
                        .filter(payment -> merchantId.equals(payment.getMerchantId()))
                        .map(payment -> new MerchantReportDTO(payment.getToken(), payment.getAmount()))
                        .collect(Collectors.toList())
        );
    }

    public synchronized void requestPaymentsList() {
        paymentsFuture = new CompletableFuture<>(); // Reset the future for a new request
        Event requestEvent = new Event("GetPaymentsRequest", new Object[]{});
        queue.publish(requestEvent);
    }

    public synchronized void handlePaymentsList(Event event) {
        List<Payment> paymentsList = event.getArgument(0, ArrayList.class);
        paymentsFuture.complete(paymentsList);
    }
}
