package ru.alfabank.huskypay.app;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author bardyshev
 * @since 29.11.2014
 */
public class PaymentInfo implements Serializable {

    private final Map<String, BigDecimal> boughtProducts;
    private final Date paymentDate;
    private final String partnerName;
    private final BigDecimal paymentSum;
    private final BigDecimal paymentVat;
    private final SerializableBitmap checkQRCode;

    public PaymentInfo(Map<String, BigDecimal> boughtProducts, Date paymentDate, String partnerName,
                       BigDecimal paymentSum, BigDecimal paymentVat, Bitmap checkQRCode) {

        this.boughtProducts = boughtProducts;
        this.paymentDate = paymentDate;
        this.partnerName = partnerName;
        this.paymentSum = paymentSum;
        this.paymentVat = paymentVat;
        this.checkQRCode = new SerializableBitmap(checkQRCode);
    }

    public Map<String, BigDecimal> getBoughtProducts() {
        return boughtProducts;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public BigDecimal getPaymentSum() {
        return paymentSum;
    }

    public BigDecimal getPaymentVat() {
        return paymentVat;
    }

    public Bitmap getCheckQRCode() {
        return checkQRCode.getBitmap();
    }
}
