package co.com.interkont.wscobra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.interkont.wscobra.api.request.FavoritosRequest;
import co.com.interkont.wscobra.dao.FavoritosRepository;
import co.com.interkont.wscobra.dto.VistaFavoritoAsignados;

@Service
public class FavoritosService {
	
	@Autowired
	FavoritosRepository favoritosDAO;
	
	
	public List<VistaFavoritoAsignados> findAll(){
		return favoritosDAO.findAll();		
	}
	
	public List<VistaFavoritoAsignados> proyectosPorUsuario(FavoritosRequest favoritosRequest){
		return  favoritosDAO.findByUsuario(favoritosRequest.getCodigousuario(), favoritosRequest.getLatitud(), favoritosRequest.getLongitud());		
	}

}
