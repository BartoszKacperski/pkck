package com.bkpp.utils;

import com.bkpp.model.Root;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class Validator {

    private Validator(){

    }

    public static void validate(String xsdAbsolutePath, Root root) throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(xsdAbsolutePath));

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.marshal(root, new DefaultHandler());
    }
}
