package behaviourtests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import messaging.Event;
import messaging.MessageQueue;
import reporting.service.DTO.CustomerReportDTO;
import reporting.service.DTO.ManagerReport;
import reporting.service.DTO.MerchantReportDTO;
import reporting.service.Payment;
import reporting.service.ReportingService;
import reporting.service.Token;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportingServiceSteps {
    List<Payment> RepoPayments;
    private CompletableFuture<Event> publishedEvent = new CompletableFuture<>();
    private MessageQueue q = new MessageQueue() {

        @Override
        public void publish(Event event) {
            publishedEvent.complete(event);
        }

        @Override
        public void addHandler(String eventType, Consumer<Event> handler) {
        }

    };

    ReportingService service = new ReportingService(q);

    @Given("a manager report generation request and some payments in the repository")
    public void a_request_is_received() {
        // create is called directly in rest so no need to emulate rest call, just emulate repo
        RepoPayments = new ArrayList<>();

        Payment payment1 = new Payment();
        payment1.setAmount(100);
        payment1.setMerchantId("sælgermanden");
        payment1.setCustomerId("købermanden");
        payment1.setToken(new Token("123"));

        Payment payment2 = new Payment();
        payment2.setAmount(200);
        payment2.setMerchantId("sælgermanden");
        payment2.setCustomerId("købermanden");
        payment2.setToken(new Token("321"));

    }

    @Then("a GetPaymentsRequest event is sent to the PaymentService")
    public void aEventIsSentToThePaymentService() {

        service.requestPaymentsList();

    }

    @Then("a list of payments is received")
    public void EmulatePaymentList() {

        service.handlePaymentsList(new Event("PaymentsList", new Object[]{RepoPayments}));

    }


    @Then("a ManagerReport is generated and is returned to the manager")
    public void aManagerReportIsGenerated() {
        CompletableFuture<ManagerReport> report = service.createManagerReport();
        assertNotNull(report);
    }

    @Given("a customer report generation request and some payments in the repository")
    public void aCustomerReportGenerationRequestIsReceived() {

        // create is called directly in rest so no need to emulate rest call, just emulate repo
        RepoPayments = new ArrayList<>();

        Payment payment1 = new Payment();
        payment1.setAmount(100);
        payment1.setMerchantId("sælgermanden");
        payment1.setCustomerId("købermanden");
        payment1.setToken(new Token("123"));

        Payment payment2 = new Payment();
        payment2.setAmount(200);
        payment2.setMerchantId("sælgermanden");
        payment2.setCustomerId("købermanden");
        payment2.setToken(new Token("321"));
        
    }

    @Then("a CustomerReport is generated and is returned to the customer")
    public void aCustomerReportIsGenerated() {
        CompletableFuture<List<CustomerReportDTO>> report = service.createCustomerReport("hans123");
        assertNotNull(report);
    }

    @Given("a merchant report generation request and some payments in the repository")
    public void aMerchantReportGenerationRequestIsReceived() {

        // create is called directly in rest so no need to emulate rest call, just emulate repo
        RepoPayments = new ArrayList<>();

        Payment payment1 = new Payment();
        payment1.setAmount(100);
        payment1.setMerchantId("sælgermanden");
        payment1.setCustomerId("købermanden");
        payment1.setToken(new Token("123"));

        Payment payment2 = new Payment();
        payment2.setAmount(200);
        payment2.setMerchantId("sælgermanden");
        payment2.setCustomerId("købermanden");
        payment2.setToken(new Token("321"));
        
    }

    @Then("a MerchantReport is generated and is returned to the merchant")
    public void aMerchantReportIsGenerated() {
        CompletableFuture<List<MerchantReportDTO>> report = service.createMerchantReport("erbert123");
        assertNotNull(report);
    }
}


