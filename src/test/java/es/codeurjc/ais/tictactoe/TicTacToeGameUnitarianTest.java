package es.codeurjc.ais.tictactoe;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeGameUnitarianTest {
	Board partida;
	Player j1;
	Player j2;

	// Automatiza la partida de tal modo que la deja en un estado critico de
	// victoria,derrota o empate

	public void iniciacionEstadosPrueba() {
		this.j1 = new Player(1, "jugador1", "Marcos");
		this.j2 = new Player(2, "jugador2", "Juan");
		this.partida = new Board();

		// j1
		partida.getCell(7).value=j1.getLabel();
		partida.getCell(7).active=false;
		// j2
		partida.getCell(6).value=j2.getLabel();
		partida.getCell(6).active=false;
		// j1
		partida.getCell(4).value=j1.getLabel();
		partida.getCell(4).active=false;
		// j2
		partida.getCell(3).value=j2.getLabel();
		partida.getCell(3).active=false;

	}

	@Test
	public void primerJugadorGana() {
		// j1
		iniciacionEstadosPrueba();
		partida.getCell(1).value=j1.getLabel();
		partida.getCell(1).active=false;
		assertThat(partida.getCellsIfWinner(j1.getLabel()).length,is(3));
		assertThat(partida.checkDraw(),is(false));
	}

	@Test
	public void primerJugadorPierde() {
		iniciacionEstadosPrueba();
		// j1
		partida.getCell(5).value=j1.getLabel();
		partida.getCell(5).active=false;
		// j2
		partida.getCell(0).value=j2.getLabel();
		partida.getCell(0).active=false;

		assertThat(partida.getCellsIfWinner(j1.getLabel()),is(nullValue()));
		assertThat(partida.checkDraw(),is(false));
	}

	@Test
	public void empate() {
		iniciacionEstadosPrueba();
		// j1
		partida.getCell(5).value=j1.getLabel();
		partida.getCell(5).active=false;
		// j2
		partida.getCell(8).value=j2.getLabel();
		partida.getCell(8).active=false;
		// j1
		partida.getCell(2).value=j1.getLabel();
		partida.getCell(2).active=false;
		// j2
		partida.getCell(1).value=j2.getLabel();
		partida.getCell(1).active=false;
		// j1
		partida.getCell(0).value=j1.getLabel();
		partida.getCell(0).active=false;
		assertThat(partida.checkDraw(),is(true));
	}
	
	
	

}
