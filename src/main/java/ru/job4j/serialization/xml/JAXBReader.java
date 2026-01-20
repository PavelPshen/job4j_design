package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.Marshaller;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.File;

public class JAXBReader {
    public static void main(String[] args) throws Exception {
        Car car = new Car(false, 5, "Wrangler", new Engine(4, 200),
                new String[] {"off-road kit", "air conditioner", "laser headlights"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        try (StringReader reader = new StringReader(xml)) {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
            File xmlFile = new File("src/main/java/ru/job4j/serialization/xml/Car.xml");
            Car resultFromXml = (Car) unmarshaller.unmarshal(xmlFile);
            System.out.println(resultFromXml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
