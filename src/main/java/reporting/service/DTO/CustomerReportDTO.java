package reporting.service.DTO;

import reporting.service.Token;

public class CustomerReportDTO {
    private Token token;
    private int amount;
    private String merchantId;

    public CustomerReportDTO(Token token, int amount, String merchantId) {
        this.token = token;
        this.amount = amount;
        this.merchantId = merchantId;
    }
}
