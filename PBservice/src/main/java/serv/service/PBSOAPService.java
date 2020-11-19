package serv.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class PBSOAPService {
	private PBMain basetalker;

	@Autowired
	public PBSOAPService(PBMain basetalker) {
		this.basetalker = basetalker;
	}

	@WebMethod
	@WebResult(name="Serv")
	public boolean insert(String order) {
		return basetalker.insert(order);
	}

	@WebMethod
	@WebResult(name="Serv")
	public byte[] update() {
		return basetalker.updatePicture();
	}
}
