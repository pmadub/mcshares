package com.mcb.mcsharesproject.services.xml;

import com.mcb.mcsharesproject.vo.RequestDocUnmarshallingResult;
import com.mcb.mcsharesproject.vo.XsdValidationErrorVO;
import com.mcb.mcsharesproject.xml.bindings.RequestDoc;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlIntegrationService {

    private ResourceLoader resourceLoader;

    public XmlIntegrationService(ResourceLoader resourceLoader)  {
        this.resourceLoader = resourceLoader;
    }

    public RequestDocUnmarshallingResult unmarshall(File xmlFile) throws JAXBException, IOException, SAXException {
        List<XsdValidationErrorVO> validationErrorList = new ArrayList<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(RequestDoc.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(createSchema());
        unmarshaller.setEventHandler(validationEvent -> {
            validationErrorList.add(new XsdValidationErrorVO(validationEvent.getLocator().getLineNumber(),
                                                             validationEvent.getLocator().getColumnNumber(),
                                                             validationEvent.getMessage()));
            return true;
        });
        RequestDoc requestDoc = (RequestDoc) unmarshaller.unmarshal(new FileReader(xmlFile));

        return CollectionUtils.isEmpty(validationErrorList) ? new RequestDocUnmarshallingResult(requestDoc)
                : new RequestDocUnmarshallingResult(validationErrorList);
    }

    /**
     * This method to create a new schema from the xsd that is provided in the project resources.
     * The additional properties are added so as to prevent XML eXternal Entity injection (XXE).
     * @return
     * @throws IOException
     * @throws SAXException
     */
    private Schema createSchema() throws IOException, SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        return factory.newSchema(getXsdFromResource().getURL());
    }

    private Resource getXsdFromResource() {
        return resourceLoader.getResource("classpath:mcshares.xsd");
    }
}
