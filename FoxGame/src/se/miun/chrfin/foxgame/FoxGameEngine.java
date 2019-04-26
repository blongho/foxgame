package se.miun.chrfin.foxgame;

import ch.rfin.foxgame.Foxgame;
import ch.rfin.foxgame.rules.State;
import ch.rfin.foxgame.rules.std.FoxgameHelper;
import se.miun.chrfin.foxgame.com.GameStatus;
import se.miun.chrfin.foxgame.setup.PlayerSetup;

/**
 * @author Christoffer Fink
 */
public class FoxGameEngine implements AiGameEngine {
	private final PlayerSetup setup;
	private State currentState;
	private Foxgame foxgame;
	private FoxgameHelper helper;

	public FoxGameEngine(PlayerSetup setup) {
		this.setup = setup;
		foxgame = new Foxgame();
		currentState = foxgame.getRoot();
		helper = new FoxgameHelper();

	}

	/**
	 * Return a move of the form "x1,y1 x2,y2".
	 */
	@Override
	public String getMove(GameStatus status) {

		if (status.isGameOver()) {
			return status.winner;
		}
		return status.move;
	}

	@Override
	public void updateState(String move) {
		if (foxgame.terminal(currentState))
			return;
		currentState = foxgame.transition(currentState, move);
	}

	@Override
	public String getPlayerName() {
		return setup.playerName;
	}

}
