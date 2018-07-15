package trng.xsd.customer_xsd.operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import trng.xsd.customer_xsd.Address;
import trng.xsd.customer_xsd.Customer;
import trng.xsd.customer_xsd.PaymentMethod;

public class CustomerApp{
	public void marshall() throws DatatypeConfigurationException, JAXBException, FileNotFoundException {
		  JAXBContext contextObj = JAXBContext.newInstance(Customer.class);  
		  
		    Marshaller marshallerObj = contextObj.createMarshaller();  
		    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		    GregorianCalendar gc=new GregorianCalendar();
		    gc.set(1992, 04, 17);
		    XMLGregorianCalendar date1=DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		    Address address=new Address("5670 George Lane","Atlanta,GA","678904");
		    PaymentMethod pm=new PaymentMethod(67890393,"DISCOVER", date1,"valid","CREDITCARD");
		 
		    Customer c=new Customer(101,"Aleena",date1, 56700.4,address, pm);
		      
		    marshallerObj.marshal(c, new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\customer-xsd\\src\\main\\resources\\customer1.xml"));  
	}
	
	public void unmarshall() throws JAXBException {
		 File file = new File("C:\\Users\\User\\eclipse-workspace\\customer-xsd\\src\\main\\resources\\customer.xml");    
         JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);    
      
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();    
         Customer c=(Customer) jaxbUnmarshaller.unmarshal(file); 
         System.out.println(c.toString());
	}
	
	public static void main(String args[]) {
		CustomerApp ca=new CustomerApp();
		try {
			ca.marshall();
			ca.unmarshall();
		} catch (FileNotFoundException | DatatypeConfigurationException e) { 
			System.out.println("File not found or Data configuration error");
			e.printStackTrace();
		} catch(JAXBException e) {
			System.out.println("JAXB Exception");
			e.printStackTrace();
		}
		
	}
}