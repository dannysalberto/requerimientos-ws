package co.com.interkont.avanzame.businnes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import co.com.interkont.avanzame.api.request.ObraFPORequest;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.bussines.interfaces.IObraFPOBussines;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.models.ObraFPO;
import co.com.interkont.avanzame.service.ObraFPOJPAServices;

@Component
public class BussinnesObraFPOServices implements IObraFPOBussines{
	
	
	@Autowired
	ObraFPOJPAServices services;

	@Override
	public ResponseEntity<?> obtenerObraFPO(Integer idobra) {
		// TODO Auto-generated method stub
		ResponseGeneric response = new ResponseGeneric(); 
		try {
				ObraFPO obraFPO = services.buscarPorId(idobra);
				if (obraFPO!=null) {
					return new ResponseEntity<ObraFPO>(obraFPO, HttpStatus.OK);
				}else {
					response.setStatus(false);
					response.setMensaje(Constantes.NO_EXISTE);
					return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
				}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return null;
	}
	

	@Override
	public ResponseEntity<?> guardarObraFPO(ObraFPORequest obraFPO) {
		// TODO Auto-generated method stub
		ResponseGeneric response = new ResponseGeneric(); 
		ObraFPO obraFPOUpdate = services.buscarPorId(obraFPO.getId());
		
		if (obraFPOUpdate == null ) {
			response.setStatus(false);
			response.setMensaje(Constantes.NO_EXISTE);
			return new ResponseEntity<ResponseGeneric>(response, HttpStatus.NOT_FOUND);
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			obraFPOUpdate.setFechaPuestaOperacion(format.parse(obraFPO.getFechaFPO()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<ObraFPO>(services.actualizar(obraFPOUpdate), HttpStatus.OK);
	}

	

}
