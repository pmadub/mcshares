package com.mcb.mcsharesproject.xml.bindings.customer;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType
public class SharesDetails {
    private int numShares;
    private BigDecimal sharePrice;

    public int getNumShares() {
        return numShares;
    }

    @XmlElement(name = "Num_Shares")
    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }

    public BigDecimal getSharePrice() {
        return sharePrice;
    }

    @XmlElement(name = "Share_Price")
    public void setSharePrice(BigDecimal sharePrice) {
        this.sharePrice = sharePrice;
    }
}
