package PB.clients;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.3.0
 * 2020-11-20T19:54:00.996+03:00
 * Generated source version: 3.3.0
 *
 */
@WebService(targetNamespace = "http://service.serv/", name = "PBSOAPService")
@XmlSeeAlso({ObjectFactory.class})
public interface PBSOAPService {

    @WebMethod
    @RequestWrapper(localName = "insert", targetNamespace = "http://service.serv/", className = "serv.service.Insert")
    @ResponseWrapper(localName = "insertResponse", targetNamespace = "http://service.serv/", className = "serv.service.InsertResponse")
    @WebResult(name = "Serv", targetNamespace = "")
    public boolean insert(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://service.serv/", className = "serv.service.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://service.serv/", className = "serv.service.UpdateResponse")
    @WebResult(name = "Serv", targetNamespace = "")
    public byte[] update();
}
