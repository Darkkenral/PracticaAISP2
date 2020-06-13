package es.codeurjc.ais.tictactoe;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeGameTestUnitarios {
	TicTacToeGame partida;
	Player j1;
	Player j2;
	// Automatiza la partida de tal modo que la deja en un estado critico de
	// victoria,derrota o empate
	@BeforeEach
	public void iniciacionEstadosPrueba() {
		 this.j1 = new Player(1, "jugador1", "Marcos");
		 this.j2 = new Player(2, "jugador2", "Juan");
		this.partida = new TicTacToeGame();
		partida.addPlayer(j1);
		partida.addPlayer(j2);
		// j1
		partida.mark(7);
		// j2
		partida.mark(6);
		// j1
		partida.mark(4);
		// j2
		partida.mark(3);

	}
	@Test
	public void primerJugadorGana() {
		// j1
		partida.mark(1);
		assertEquals(3,partida.checkWinner().pos.length);
		assertEquals(partida.checkWinner().win,true);
		assertEquals(partida.checkDraw(),false);
	}
	@Test
	public void primerJugadorPierde() {
		// j1
		partida.mark(5);
		// j2
		partida.mark(0);

		assertEquals(null,partida.checkWinner().pos);
		assertEquals(partida.checkWinner().win,false);
		assertEquals(partida.checkDraw(),false);
	}
	@Test
	public void empate() {
		// j1
		partida.mark(5);
		// j2
		partida.mark(8);
		// j1
		partida.mark(2);
		// j2
		partida.mark(1);
		// j1
		partida.mark(0);
		assertThat(partida.checkDraw(), is(true));
	}

}
