package co.com.interkont.wsmiobra.interfaces;
import co.com.interkont.wsmiobra.dto.Obra;
import co.com.interkont.wsmiobra.models.ResponseGeneric;

public interface ICalculosPeriodo {

	boolean generarPeriodos(Obra obra);
	ResponseGeneric planeacionPorPeriodo(Integer idObra);

}