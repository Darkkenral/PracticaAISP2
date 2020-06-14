package es.codeurjc.ais.tictactoe;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

class MockTicTacToeGameTest {

	TicTacToeGame partida = new TicTacToeGame();
	Connection c1 = mock(Connection.class);
	Connection c2 = mock(Connection.class);
	Player j1 = new Player(1, "jugador1", "Marcos");
	Player j2 = new Player(2, "jugador2", "Juan");

	@BeforeEach
	public void mockIniciacionPruebas() {
		reset(c1);
		reset(c2);
		partida.addConnection(c1);
		partida.addConnection(c2);

		partida.addPlayer(j1);
		partida.addPlayer(j2);
		verify(c1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(j1, j2)));
		verify(c2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(j1, j2)));

		// j1
		assertThat(partida.checkTurn(j1.getId()), is(true));
		partida.mark(7);
		assertThat(partida.checkTurn(j1.getId()), is(false));
		// j2
		partida.mark(6);
		assertThat(partida.checkTurn(j2.getId()), is(false));
		// j1
		partida.mark(4);
		assertThat(partida.checkTurn(j1.getId()), is(false));
		// j2
		partida.mark(3);
		assertThat(partida.checkTurn(j2.getId()), is(false));
	}

	@Test
	public void primerJugadorGana() {
		// j1
		partida.mark(1);
		assertThat(partida.checkTurn(j1.getId()), is(true));

		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		WinnerValue event = argumento.getValue();
		assertThat(event.player, is(j1));
		assertThat(event.pos.length, is(3));

	}

	@Test
	public void primerJugadorPierde() {
		// j1
		partida.mark(5);
		assertThat(partida.checkTurn(j1.getId()), is(false));
		// j2
		partida.mark(0);
		assertThat(partida.checkTurn(j2.getId()), is(true));

		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		WinnerValue event = argumento.getValue();
		assertThat(event.player, is(j2));
		assertThat(event.pos.length, is(3));

	}

	@Test
	public void empate() {
		// j1
		partida.mark(5);
		assertThat(partida.checkTurn(j1.getId()), is(false));
		// j2
		partida.mark(8);
		assertThat(partida.checkTurn(j2.getId()), is(false));
		// j1
		partida.mark(2);
		assertThat(partida.checkTurn(j1.getId()), is(false));
		// j2
		partida.mark(1);
		assertThat(partida.checkTurn(j2.getId()), is(false));
		// j1
		partida.mark(0);
		assertThat(partida.checkTurn(j1.getId()), is(true));

		ArgumentCaptor<WinnerValue> argumento = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argumento.capture());
		WinnerValue event = argumento.getValue();

		assertEquals(event, null);
		assertThat(partida.checkDraw(), is(true));

	}

}
