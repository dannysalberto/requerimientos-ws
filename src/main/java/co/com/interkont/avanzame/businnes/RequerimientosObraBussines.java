package co.com.interkont.avanzame.businnes;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.interkont.avanzame.api.request.RequerimientoObraRequest;
import co.com.interkont.avanzame.api.request.RequerimientoObraRequestUpd;
import co.com.interkont.avanzame.api.response.RequerimientoObraResponse;
import co.com.interkont.avanzame.api.response.ResponseGeneric;
import co.com.interkont.avanzame.config.Constantes;
import co.com.interkont.avanzame.models.RequerimientoObra;
import co.com.interkont.avanzame.service.ObraJPAServices;
import co.com.interkont.avanzame.service.RequerimientoObraServices;
import co.com.interkont.avanzame.service.RequerimientoServices;

@Component
public class RequerimientosObraBussines {


	@Autowired
	RequerimientoObraServices repo;
	
	@Autowired
	RequerimientoServices repoRequerimiento;
	
	@Autowired
	ObraJPAServices repoObra;
	
	int contador=0;
	
	public List<RequerimientoObraResponse> obtenerRequerimientos(Integer obraId) {
		// TODO Auto-generated method stub
	    List<RequerimientoObra> data= repo.obtenerRequerimientosObra(obraId);
	    
	    List<RequerimientoObraResponse> lstresp = new ArrayList();
	    
	    data.forEach(arg0->{
	    	RequerimientoObraResponse resp = new RequerimientoObraResponse();
	    	resp.setId(arg0.getId());
	    	resp.setObra(repoObra.buscarPorId(arg0.getObraid()));
	    	resp.setRequerimiento(repoRequerimiento.buscarPorId(arg0.getRequerimientoid()));
	    	resp.setValor(arg0.getValor());
	    	lstresp.add(resp);
	    });
		return lstresp;
	}
	
	public ResponseGeneric crear(List<RequerimientoObraRequest> requerimiento) {

		requerimiento.forEach(req->{
			RequerimientoObra obj = new RequerimientoObra();
			obj.setObraid(req.getObraid());
			obj.setRequerimientoid(req.getRequerimientoid());
			obj.setValor(req.getValor());
			repo.crear(obj);	
		});
		ResponseGeneric resp = new ResponseGeneric();
		resp.setStatus(true);
		resp.setMensaje(Constantes.REQUERIMIENTOS_DE_OBRA_ACTUALIZADOS_CORRECTAMENTE);
		return resp;
		
	}

	public ResponseGeneric actualizar(List<RequerimientoObraRequestUpd> requerimiento) {

		contador=0;
		ResponseGeneric resp = new ResponseGeneric();
		requerimiento.forEach(req->{
			try {
				RequerimientoObra obj = repo.buscarPorId(req.getId());
				obj.setObraid(req.getObraid());
				obj.setRequerimientoid(req.getRequerimientoid());
				obj.setValor(req.getValor());
				contador++;
				repo.actualizar(obj);	
			}catch (Exception e) {
				// TODO: handle exception
				resp.setStatus(false);
				resp.setMensaje(e.getMessage());
			}
				
		});
		if (contador>0) {
			resp.setStatus(true);
			resp.setMensaje(Constantes.REQUERIMIENTOS_DE_OBRA_ACTUALIZADOS_CORRECTAMENTE);
		}
		return resp;			
	}

	public ResponseGeneric borrar(Integer id) {
		
		ResponseGeneric resp = new ResponseGeneric();
		try {
			repo.borrar(id);		
			resp.setStatus(true);
			resp.setMensaje(Constantes.REGISTRO_ELIMINADO);
		}catch (Exception e) {
			// TODO: handle exception
			resp.setStatus(false);
			resp.setMensaje(e.getMessage());
			
		}
		return resp;
		
		
	}

}
