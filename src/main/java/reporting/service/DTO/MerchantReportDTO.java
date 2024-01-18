package reporting.service.DTO;

import reporting.service.Token;

public class MerchantReportDTO {
    private Token token;
    private int amount;

    public MerchantReportDTO(Token token, int amount) {
        this.token = token;
        this.amount = amount;
    }
}
