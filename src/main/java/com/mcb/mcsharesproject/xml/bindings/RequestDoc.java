package com.mcb.mcsharesproject.xml.bindings;

import com.mcb.mcsharesproject.xml.bindings.customer.DataItemCustomer;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "RequestDoc")
@XmlAccessorType
public class RequestDoc {

    private String docDate;
    private String docRef;
    private List<DataItemCustomer> customers;

    public String getDocDate() {
        return docDate;
    }

    @XmlElement(name = "Doc_Date")
    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getDocRef() {
        return docRef;
    }

    @XmlElement(name = "Doc_Ref")
    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public List<DataItemCustomer> getCustomers() {
        return customers;
    }

    @XmlElementWrapper(name = "Doc_Data")
    @XmlElement(name = "DataItem_Customer")
    public void setCustomers(List<DataItemCustomer> customers) {
        this.customers = customers;
    }
}
