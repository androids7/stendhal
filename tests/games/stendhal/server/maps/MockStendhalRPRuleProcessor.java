package games.stendhal.server.maps;

import games.stendhal.server.StendhalRPRuleProcessor;

public class MockStendhalRPRuleProcessor extends StendhalRPRuleProcessor {
	public static StendhalRPRuleProcessor get() {
		if (instance == null) {
			instance = new MockStendhalRPRuleProcessor();
		}
		return instance;
	}

	@Override
	public void addGameEvent(String source, String event, String... params) {
	
	}

	@Override
	public int getTurn() {
		return 0;
	}
}
