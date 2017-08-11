package ar.edu.unq.ciu.monsters.evento;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.ciu.monsters.dominio.Banda;
import ar.edu.unq.ciu.monsters.dominio.Disco;

public abstract class EventoMultiBanda extends Evento {
	private List<ParticipacionEnEvento> bandasEnEvento = new ArrayList<>();
	
	public EventoMultiBanda(String nombre) {
		super(nombre);
	}

	@Override
	public List<Banda> getBandas() { 
		return this.bandasEnEvento.stream().map(banda -> banda.getBanda()).collect(Collectors.toList()); 
	}

	@Override
	public int getDuracion() { 
		return this.bandasEnEvento.stream().mapToInt(banda -> banda.getMinutos()).sum(); 
	}

	@Override
	public void addToBandas(Banda banda, Disco discoQuePresenta, int minutos) { 
		if (this.canAddToBandas(banda, discoQuePresenta, minutos)) {
			this.bandasEnEvento.add(new ParticipacionEnEvento(banda, discoQuePresenta, minutos));
		} else {
			throw new RuntimeException("no se puede agregar la banda tal cual lo pedido");
		}
	}

	public List<ParticipacionEnEvento> getBandasEnEvento() {
		return bandasEnEvento;
	}

	public void setBandasEnEvento(List<ParticipacionEnEvento> bandasEnEvento) {
		this.bandasEnEvento = bandasEnEvento;
	}
	
	
}
