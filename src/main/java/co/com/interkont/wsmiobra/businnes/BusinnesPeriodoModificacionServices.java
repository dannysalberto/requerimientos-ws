package co.com.interkont.wsmiobra.businnes;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.com.interkont.wsmiobra.config.Constantes;
import co.com.interkont.wsmiobra.models.ActividadObraModificacion;
import co.com.interkont.wsmiobra.models.ObraChangeDate;
import co.com.interkont.wsmiobra.models.ObraModificacion;
import co.com.interkont.wsmiobra.models.ResponseGeneric;
import co.com.interkont.wsmiobra.service.ActividadObraModificacionService;
import co.com.interkont.wsmiobra.service.ObraModificacionService;

@Component
public class BusinnesPeriodoModificacionServices {
	
	@Autowired
	ObraModificacionService serviceObra;
	
	@Autowired
	ActividadObraModificacionService serviceActividadModificacion;
	
	public int i=0;
	public boolean itero = false;
	public int cantidad=0;

	
	/**
	 * @param request
	 */
	public void ajustarFechaObra(ObraModificacion obraMod) {

		ObraModificacion obra = serviceObra.buscarPorId(obraMod.getId());
		if(obraMod.getNewfechafin()!=null )
		{
			cantidad = 0;
				List<ActividadObraModificacion> lstactividad=obra.getActividadesObra();		
				lstactividad.forEach((result)->{
					if(obra.getNewfechafin().compareTo(result.getFechafin())<0)
					{
						result.setNewfechafin(obra.getNewfechafin());
						System.out.println(result);
						cantidad++;	
					}			
					serviceActividadModificacion.guardar(result);
				});				
					
		}
	}
	
	public ResponseGeneric valiChangeDate(ObraChangeDate objeto) {		
		ResponseGeneric response = new ResponseGeneric();
		if (objeto.getId()==null || objeto.getFechaFinObra()==null|| objeto.getFechaFinObra()=="" || objeto.getFechaInicioObra()==null|| objeto.getFechaInicioObra()=="") {
			response.setStatus(false);
			response.setMessage(Constantes.FALTAN_ATRIBUTOS);
			return response;
		}
		return null;			
	}

}
